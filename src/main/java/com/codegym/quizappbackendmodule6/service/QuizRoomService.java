package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.QuizRoom;
import com.codegym.quizappbackendmodule6.model.dto.quiz_room.QuizResultDto;

import java.util.List;

public interface QuizRoomService {
    QuizRoom createQuizRoom(Long quizId, Long userId, Integer maxParticipants);
    QuizRoom joinQuizRoom(String roomCode, Long userId);
    QuizRoom startQuiz(String roomCode);
    QuizRoom endQuiz(String roomCode);
    QuizRoom restartQuiz(String roomCode);
    QuizRoom getQuizRoomByCode(String roomCode);
    List<QuizResultDto> getQuizResults(String roomCode);
}
