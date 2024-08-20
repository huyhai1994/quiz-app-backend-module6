package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.QuizMessage;
import com.codegym.quizappbackendmodule6.model.QuizRoom;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.QuizResultDto;
import com.codegym.quizappbackendmodule6.repository.QuizRoomRepository;
import com.codegym.quizappbackendmodule6.service.QuizRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuizWebSocketController {
    private final QuizRoomService quizRoomService;
    private final SimpMessagingTemplate messagingTemplate;
    private final QuizRoomRepository quizRoomRepository;

    @MessageMapping("/quiz.joinRoom")
    @SendTo("/topic/room/{roomCode}")
    public QuizMessage joinRoom(@DestinationVariable String roomCode, @Payload QuizMessage quizMessage) {
        try {
            QuizRoom quizRoom = quizRoomService.joinQuizRoom(roomCode, Long.parseLong(quizMessage.getSender()));
            quizMessage.setType(QuizMessage.MessageType.JOIN);
            quizMessage.setContent("User " + quizMessage.getSender() + " joined the room");
            quizMessage.setRoomCode(roomCode);
            return quizMessage;
        } catch (RuntimeException e) {
            quizMessage.setType(QuizMessage.MessageType.ERROR);
            quizMessage.setContent(e.getMessage());
            return quizMessage;
        }
    }

    @MessageMapping("/quiz.startQuiz")
    @SendTo("/topic/room/{roomCode}")
    public QuizMessage startQuiz(@DestinationVariable String roomCode, @Payload QuizMessage quizMessage) {
        try {
            QuizRoom quizRoom = quizRoomService.startQuiz(roomCode);
            quizMessage.setType(QuizMessage.MessageType.QUIZ_START);
            quizMessage.setContent("Quiz started");
            quizMessage.setRoomCode(roomCode);
            return quizMessage;
        } catch (RuntimeException e) {
            quizMessage.setType(QuizMessage.MessageType.ERROR);
            quizMessage.setContent(e.getMessage());
            return quizMessage;
        }
    }

    @MessageMapping("/quiz.endQuiz")
    @SendTo("/topic/room/{roomCode}")
    public QuizMessage endQuiz(@DestinationVariable String roomCode, @Payload QuizMessage quizMessage) {
        try {
            QuizRoom quizRoom = quizRoomService.endQuiz(roomCode);
            List<QuizResultDto> results = quizRoomService.getQuizResults(roomCode);
            quizMessage.setType(QuizMessage.MessageType.QUIZ_END);
            quizMessage.setContent("Quiz ended");
            quizMessage.setRoomCode(roomCode);
            quizMessage.setResults(results);
            return quizMessage;
        } catch (RuntimeException e) {
            quizMessage.setType(QuizMessage.MessageType.ERROR);
            quizMessage.setContent(e.getMessage());
            return quizMessage;
        }
    }

    @MessageMapping("/quiz.restartQuiz")
    @SendTo("/topic/room/{roomCode}")
    public QuizMessage restartQuiz(@DestinationVariable String roomCode, @Payload QuizMessage quizMessage) {
        try {
            QuizRoom quizRoom = quizRoomService.restartQuiz(roomCode);
            quizMessage.setType(QuizMessage.MessageType.QUIZ_RESTART);
            quizMessage.setContent("Quiz restarted");
            quizMessage.setRoomCode(roomCode);
            return quizMessage;
        } catch (RuntimeException e) {
            quizMessage.setType(QuizMessage.MessageType.ERROR);
            quizMessage.setContent(e.getMessage());
            return quizMessage;
        }
    }

    @MessageMapping("/quiz.submitAnswer")
    public void submitAnswer(@Payload QuizMessage quizMessage) {
        // Logic to handle answer submission
        // For now, we'll just broadcast the message to all participants
        messagingTemplate.convertAndSend("/topic/room/" + quizMessage.getRoomCode(), quizMessage);
    }

    @Scheduled(fixedRate = 60000)
    public void checkAndEndExpiredQuizzes() {
        List<QuizRoom> activeRooms = quizRoomRepository.findByStatus(QuizRoom.RoomStatus.ACTIVE);
        for (QuizRoom room : activeRooms) {
            if (room.getStartAt().plusSeconds(room.getQuiz().getQuizTime()).isBefore(LocalDateTime.now())) {
                QuizRoom endedRoom = quizRoomService.endQuiz(room.getRoomCode());
                QuizMessage endMessage = new QuizMessage("Quiz time is up", "System", QuizMessage.MessageType.QUIZ_END, room.getRoomCode());
                endMessage.setResults(quizRoomService.getQuizResults(room.getRoomCode()));
                messagingTemplate.convertAndSend("/topic/room/" + room.getRoomCode(), endMessage);
            }
        }
    }

    @MessageMapping("/quiz.sendMessage")
    @SendTo("/topic/public")
    public QuizMessage sendMessage(@Payload QuizMessage quizMessage) {
        return quizMessage;
    }

    @MessageMapping("/quiz.addUser")
    @SendTo("/topic/public")
    public QuizMessage addUser(@Payload QuizMessage quizMessage) {
        return quizMessage;
    }
}
