package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.QuizRoom;
import com.codegym.quizappbackendmodule6.model.QuizSession;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.QuizResultDto;
import com.codegym.quizappbackendmodule6.repository.*;
import com.codegym.quizappbackendmodule6.service.QuizRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizRoomServiceImpl implements QuizRoomService {
    private final QuizRoomRepository quizRoomRepository;
    private final QuizRepository quizRepository;
    private final UserRepository userRepository;
    private final QuizSessionRepository quizSessionRepository;
    private final ResultRepository resultRepository;

    @Override
    @Transactional
    public QuizRoom createQuizRoom(Long quizId, Long userId, Integer maxParticipants) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        QuizRoom quizRoom = new QuizRoom();
        quizRoom.setQuiz(quiz);
        quizRoom.setCreateBy(user);
        quizRoom.setRoomCode(generateUniqueRoomCode());
        quizRoom.setMaxParticipants(maxParticipants);
        quizRoom.setStatus(QuizRoom.RoomStatus.WAITING);

        return quizRoomRepository.save(quizRoom);
    }

    @Override
    public QuizRoom joinQuizRoom(String roomCode, Long userId) {
        QuizRoom quizRoom = quizRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Quiz room not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (quizRoom.getStatus() != QuizRoom.RoomStatus.WAITING) {
            throw new RuntimeException("Quiz room is not in WAITING status");
        }

        if (quizRoom.getCurrentParticipants() >= quizRoom.getMaxParticipants()) {
            throw new RuntimeException("Quiz room is over max participants");
        }

        QuizSession quizSession = new QuizSession();
        quizSession.setQuizRoom(quizRoom);
        quizSession.setUser(user);
        quizSessionRepository.save(quizSession);

        quizRoom.setCurrentParticipants(quizRoom.getCurrentParticipants() + 1);
        QuizRoom savedQuizRoom = quizRoomRepository.save(quizRoom);
        System.out.println("Saved QuizRoom: " + savedQuizRoom);
        return savedQuizRoom;
    }

    @Override
    @Transactional
    public QuizRoom startQuiz(String roomCode) {
        QuizRoom quizRoom = quizRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Quiz room not found"));
        if (quizRoom.getStatus() != QuizRoom.RoomStatus.WAITING) {
            throw new RuntimeException("Quiz room is not in WAITING status");
        }
        quizRoom.setStatus(QuizRoom.RoomStatus.ACTIVE);
        quizRoom.setStartAt(LocalDateTime.now());
        return quizRoomRepository.save(quizRoom);
    }

    @Override
    public QuizRoom endQuiz(String roomCode) {
        QuizRoom quizRoom = quizRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Quiz room not found"));
        if (quizRoom.getStatus() != QuizRoom.RoomStatus.ACTIVE) {
            throw new RuntimeException("Quiz room is not in ACTIVE status");
        }
        quizRoom.setStatus(QuizRoom.RoomStatus.COMPLETED);
        quizRoom.setEndAt(LocalDateTime.now());
        return quizRoomRepository.save(quizRoom);
    }

    @Override
    public QuizRoom restartQuiz(String roomCode) {
        QuizRoom quizRoom = quizRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Quiz room not found"));
        if (quizRoom.getStatus() != QuizRoom.RoomStatus.COMPLETED) {
            throw new RuntimeException("Quiz room is not in COMPLETED status");
        }
        quizRoom.setStatus(QuizRoom.RoomStatus.WAITING);
        quizRoom.setStartAt(null);
        quizRoom.setEndAt(null);
        quizRoom.setCurrentParticipants(0);
        return quizRoomRepository.save(quizRoom);
    }

    @Override
    public QuizRoom getQuizRoomByCode(String roomCode) {
        return quizRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Quiz room not found"));
    }

    @Override
    public List<QuizResultDto> getQuizResults(String roomCode) {
        QuizRoom quizRoom = quizRoomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new RuntimeException("Quiz room not found"));

        if (quizRoom.getStatus() != QuizRoom.RoomStatus.COMPLETED) {
            throw new RuntimeException("Quiz is not completed yet");
        }

        return resultRepository.findByQuizRoom(quizRoom)
                .stream()
                .map(result -> new QuizResultDto(
                        result.getId(),
                        result.getUser().getName(),
                        result.getFinishTime(),
                        result.getScore(),
                        result.getCorrectAnswers(),
                        result.getIncorrectAnswers()
                ))
                .collect(Collectors.toList());
    }

    private String generateUniqueRoomCode() {
        String roomCode;
        do {
            roomCode = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        } while (quizRoomRepository.findByRoomCode(roomCode).isPresent());
        return roomCode;
    }
}