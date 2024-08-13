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
    @Transactional
    public Result endQuiz(Long resultId, List<UserAnswerDto> userAnswers) {
        if (userAnswers == null || userAnswers.isEmpty()) {
            throw new IllegalArgumentException("No user answers provided");
        }

        // Lưu các câu trả lời của người dùng vào cơ sở dữ liệu
        List<UserAnswer> savedAnswers = new ArrayList<>();

        for (UserAnswerDto answerDto : userAnswers) {
            User user = userService.findById(answerDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Question question = questionService.findById(answerDto.getQuestionId())
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            Option option = optionService.findById(answerDto.getOptionId())
                    .orElseThrow(() -> new RuntimeException("Option not found"));

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setUser(user); // Gán người dùng đã trả lời
            userAnswer.setQuestion(question); // Gán câu hỏi liên quan
            userAnswer.setOption(option); // Gán tùy chọn đã chọn
            userAnswer.setAnsweredAt(LocalDateTime.now()); // Gán thời gian trả lời

            savedAnswers.add(userAnswer);
        }

        savedAnswers = userAnswerRepository.saveAll(savedAnswers);

        // Tìm kết quả theo resultId
        Result result = resultRepository.findById(resultId)
                .orElseThrow(() -> new RuntimeException("Result not found"));

        // Tính toán số câu trả lời đúng
        int correctAnswers = 0;
        int totalOptions = savedAnswers.size();

        for (UserAnswer userAnswer : savedAnswers) {
            Option selectedOption = userAnswer.getOption();
            if (selectedOption != null && Boolean.TRUE.equals(selectedOption.getIsCorrect())) {
                correctAnswers++;
            }
        }

        // Tính điểm và cập nhật thời gian kết thúc
        double score = ((double) correctAnswers / totalOptions) * 100;
        result.setScore((long) score);
        result.setFinishTime(LocalDateTime.now());

        // Lưu kết quả vào cơ sở dữ liệu
        return resultRepository.save(result);
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
