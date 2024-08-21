package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.QuizMessage;
import com.codegym.quizappbackendmodule6.model.QuizRoom;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.CreateQuizRoomRequest;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.JoinQuizRoomResponseDto;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.QuizResultDto;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.QuizRoomResponseDto;
import com.codegym.quizappbackendmodule6.service.QuizRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/quiz-rooms")
@RequiredArgsConstructor
public class QuizRoomController {
    private final QuizRoomService quizRoomService;
    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping
    public ResponseEntity<QuizRoomResponseDto> createQuizRoom(@RequestBody CreateQuizRoomRequest request) {
        QuizRoom quizRoom = quizRoomService.createQuizRoom(request.getQuizId(), request.getUserId(), request.getMaxParticipants());
        return ResponseEntity.ok(QuizRoomResponseDto.fromQuizRoom(quizRoom));
    }

    @PostMapping("/{roomCode}/join")
    public ResponseEntity<JoinQuizRoomResponseDto> joinQuizRoom(@PathVariable String roomCode, @RequestParam Long userId) {
        QuizRoom quizRoom = quizRoomService.joinQuizRoom(roomCode, userId);
        // Send WebSocket notification about new user joining
        messagingTemplate.convertAndSend(
                "/topic/room/" + roomCode,
                new QuizMessage("New user joined", userId.toString(), QuizMessage.MessageType.JOIN, roomCode)
        );
        return ResponseEntity.ok(JoinQuizRoomResponseDto.fromQuizRoom(quizRoom, userId));
    }

    @PostMapping("/{roomCode}/start")
    public ResponseEntity<QuizRoomResponseDto> startQuiz(@PathVariable String roomCode) {
        QuizRoom quizRoom = quizRoomService.startQuiz(roomCode);
        messagingTemplate.convertAndSend(
                "/topic/room/" + roomCode,
                new QuizMessage("Quiz started", "System", QuizMessage.MessageType.QUIZ_START, roomCode)
        );
        return ResponseEntity.ok(QuizRoomResponseDto.fromQuizRoom(quizRoom));
    }

    @PostMapping("/{roomCode}/end")
    public ResponseEntity<QuizRoomResponseDto> endQuiz(@PathVariable String roomCode) {
        QuizRoom quizRoom = quizRoomService.endQuiz(roomCode);
        messagingTemplate.convertAndSend(
                "/topic/room/" + roomCode,
                new QuizMessage("Quiz ended", "System", QuizMessage.MessageType.QUIZ_END, roomCode)
        );
        return ResponseEntity.ok(QuizRoomResponseDto.fromQuizRoom(quizRoom));
    }

    @PostMapping("/{roomCode}/restart")
    public ResponseEntity<QuizRoomResponseDto> restartQuiz(@PathVariable String roomCode) {
        QuizRoom quizRoom = quizRoomService.restartQuiz(roomCode);
        return ResponseEntity.ok(QuizRoomResponseDto.fromQuizRoom(quizRoom));
    }

    @GetMapping("/{roomCode}")
    public ResponseEntity<QuizRoomResponseDto> getQuizRoom(@PathVariable String roomCode) {
        QuizRoom quizRoom = quizRoomService.getQuizRoomByCode(roomCode);
        return ResponseEntity.ok(QuizRoomResponseDto.fromQuizRoom(quizRoom));
    }

    @GetMapping("/{roomCode}/results")
    public ResponseEntity<List<QuizResultDto>> getQuizResults(@PathVariable String roomCode) {
        List<QuizResultDto> results = quizRoomService.getQuizResults(roomCode);
        return ResponseEntity.ok(results);
    }
}
