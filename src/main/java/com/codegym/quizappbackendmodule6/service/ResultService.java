package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.Result;
import com.codegym.quizappbackendmodule6.model.UserAnswer;
import com.codegym.quizappbackendmodule6.model.dto.QuizHistoryDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizResultDTO;
import com.codegym.quizappbackendmodule6.model.dto.ResultHistoryDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserAnswerDto;
import org.springframework.data.repository.query.Param;

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
}
