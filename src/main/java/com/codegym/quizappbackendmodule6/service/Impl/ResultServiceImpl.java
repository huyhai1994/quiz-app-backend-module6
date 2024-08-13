package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.*;
import com.codegym.quizappbackendmodule6.model.dto.QuizHistoryDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizResultDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserAnswerDto;
import com.codegym.quizappbackendmodule6.repository.ResultRepository;
import com.codegym.quizappbackendmodule6.repository.UserAnswerRepository;
import com.codegym.quizappbackendmodule6.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final UserService userService;
    private final QuizService quizService;
    private final UserAnswerRepository userAnswerRepository;
    private final QuestionService questionService;
    private final OptionService optionService;

    @Override
    @Transactional
    public Result startQuiz(Long userId, Long quizId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Quiz quiz = quizService.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setStartTime(LocalDateTime.now());
        return resultRepository.save(result);
    }





    @Override
    public QuizResultDTO getQuizResultById(Long resultId) {
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        int correctAnswers = (int) userAnswerRepository.countByUserAndQuestion_QuizzesAndOption_IsCorrect(result.getUser(), result.getQuiz(), true);
        int incorrectAnswers = (int) userAnswerRepository.countByUserAndQuestion_QuizzesAndOption_IsCorrect(result.getUser(), result.getQuiz(), false);

        return new QuizResultDTO(
                result.getUser().getName(),
                result.getFinishTime(),
                result.getScore(),
                correctAnswers,
                incorrectAnswers
        );
    }

    @Override
    public List<QuizHistoryDTO> getQuizHistoryByUserId(Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Result> results = resultRepository.findByUser(user);

        // Chuyển đổi danh sách kết quả thành danh sách DTO
        List<QuizHistoryDTO> historyList = results.stream()
                .map(result -> {
                    long duration = java.time.Duration.between(result.getStartTime(), result.getFinishTime()).toMinutes();
                    return new QuizHistoryDTO(
                            result.getQuiz().getTitle(),
                            result.getFinishTime(),
                            duration,
                            result.getScore(),
                            (int) results.stream()
                                    .filter(r -> r.getQuiz().getTitle().equals(result.getQuiz().getTitle()))
                                    .count()
                    );
                })
                .sorted((r1, r2) -> r2.getFinishTime().compareTo(r1.getFinishTime())) // Sắp xếp theo thời gian thi mới nhất
                .collect(Collectors.toList());

        return historyList;
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        return quizService.findById(id);
    }
}
