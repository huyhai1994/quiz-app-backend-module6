package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.Result;
import com.codegym.quizappbackendmodule6.model.dto.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ResultService {
    Result startQuiz(Long userId, Long quizId);

    Result endQuiz(Long resultId, List<UserAnswerDto> userAnswers);

    List<QuizHistoryDTO> getQuizHistoryByUserId(Long userId);

    QuizResultDTO getQuizResultById(Long resultId);

    Optional<Quiz> getQuizById(Long id);

    Long findRankByScore(BigDecimal score);

    ResultHistoryDTO finDetailHistory(Long id);

    List<Result> findByUserId(Long userId, boolean status);

    List<HistoryStudentExam> findStudentExamByUserId(Long userId);
}
