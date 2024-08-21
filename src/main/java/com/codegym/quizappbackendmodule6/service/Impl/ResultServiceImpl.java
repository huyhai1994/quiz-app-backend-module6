package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.*;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.repository.ResultRepository;
import com.codegym.quizappbackendmodule6.repository.UserAnswerRepository;
import com.codegym.quizappbackendmodule6.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
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
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Quiz quiz = quizService.findById(quizId).orElseThrow(() -> new RuntimeException("Quiz not found"));

        Result result = new Result();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setStartTime(LocalDateTime.now());
        result.setStatus(false);
        return resultRepository.save(result);
    }

    @Override
    @Transactional
    public Result endQuiz(Long resultId, List<UserAnswerDto> userAnswers) {
        // Tạo danh sách lưu các đối tượng UserAnswer
        List<UserAnswer> savedAnswers = new ArrayList<>();
        // Map lưu các câu trả lời của người dùng theo câu hỏi
        Map<Long, List<UserAnswer>> userAnswersByQuestion = new HashMap<>();

        // Lặp qua các UserAnswerDto và tạo UserAnswer tương ứng
        for (UserAnswerDto answerDto : userAnswers) {
            User user = userService.findById(answerDto.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

            Question question = questionService.findById(answerDto.getQuestionId()).orElseThrow(() -> new RuntimeException("Question not found"));

            Option option = optionService.findById(answerDto.getOptionId()).orElseThrow(() -> new RuntimeException("Option not found"));

            UserAnswer userAnswer = new UserAnswer();
            userAnswer.setUser(user);
            userAnswer.setQuestion(question);
            userAnswer.setOption(option);
            userAnswer.setAnsweredAt(LocalDateTime.now());

            savedAnswers.add(userAnswer);

            userAnswersByQuestion.computeIfAbsent(question.getId(), k -> new ArrayList<>()).add(userAnswer);
        }

        // Lưu tất cả các UserAnswer vào cơ sở dữ liệu
        savedAnswers = userAnswerRepository.saveAll(savedAnswers);

        // Tìm đối tượng Result tương ứng với resultId
        Result result = resultRepository.findById(resultId).orElseThrow(() -> new RuntimeException("Result not found"));

        int correctAnswers = 0;
        int incorrectAnswers = 0;

        // Xử lý các câu trả lời theo từng câu hỏi
        for (Map.Entry<Long, List<UserAnswer>> entry : userAnswersByQuestion.entrySet()) {
            Question question = entry.getValue().get(0).getQuestion();
            List<UserAnswer> answersForQuestion = entry.getValue();

            // Xử lý câu hỏi có một đáp án đúng
            if (question.getQuestionType().getTypeName().equals("ONE")) {
                if (answersForQuestion.size() == 1) {
                    Option selectedOption = answersForQuestion.get(0).getOption();
                    if (selectedOption != null && Boolean.TRUE.equals(selectedOption.getIsCorrect())) {
                        correctAnswers++;
                    } else {
                        incorrectAnswers++;
                    }
                } else {
                    incorrectAnswers++;
                }
            }
            // Xử lý câu hỏi có nhiều đáp án đúng
            else if (question.getQuestionType().getTypeName().equals("MANY")) {
                List<Option> correctOptions = optionService.findCorrectOptionsByQuestionId(question.getId());
                if (answersForQuestion.size() == correctOptions.size() && correctOptions.stream().allMatch(co -> answersForQuestion.stream().anyMatch(ua -> ua.getOption().getId().equals(co.getId())))) {
                    correctAnswers++;
                } else {
                    incorrectAnswers++;
                }
            }
            // Xử lý câu hỏi TRUE_FALSE
            else if (question.getQuestionType().getTypeName().equals("TRUE_FALSE")) {
                if (answersForQuestion.size() == 1) {
                    Option selectedOption = answersForQuestion.get(0).getOption();
                    if (selectedOption != null && Boolean.TRUE.equals(selectedOption.getIsCorrect())) {
                        correctAnswers++;
                    } else {
                        incorrectAnswers++;
                    }
                } else {
                    incorrectAnswers++;
                }
            }
        }

        // Tính toán điểm số
        int totalQuestions = userAnswersByQuestion.size();
        BigDecimal score = BigDecimal.ZERO;
        if (totalQuestions > 0) {
            score = new BigDecimal((double) correctAnswers / totalQuestions * 100);
            score = score.setScale(2, RoundingMode.HALF_UP);
        }

        // Cập nhật thông tin kết quả
        result.setCorrectAnswers((long) correctAnswers);
        result.setIncorrectAnswers((long) incorrectAnswers);
        result.setScore(score);
        result.setFinishTime(LocalDateTime.now());
        result.setStatus(true);

        // Lưu kết quả vào cơ sở dữ liệu và trả về đối tượng Result
        return resultRepository.save(result);
    }


    @Override
    public QuizResultDTO getQuizResultById(Long resultId) {
        Result result = resultRepository.findById(resultId).orElseThrow(() -> new RuntimeException("Result not found"));

        return new QuizResultDTO(result.getId(), result.getUser().getName(), result.getFinishTime(), result.getScore(), result.getCorrectAnswers(), result.getIncorrectAnswers());
    }

    @Override
    public List<QuizHistoryDTO> getQuizHistoryByUserId(Long userId) {
        User user = userService.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Result> results = resultRepository.findByUser(user);

        // Chuyển đổi danh sách kết quả thành danh sách DTO
        List<QuizHistoryDTO> historyList = results.stream().map(result -> {
            Duration examDuration = Duration.between(result.getStartTime(), result.getFinishTime());
            String formattedDuration = formatDuration(examDuration);
            return new QuizHistoryDTO(result.getId(), result.getQuiz().getTitle(), result.getFinishTime(), formattedDuration, result.getScore(), (int) results.stream().filter(r -> r.getQuiz().getTitle().equals(result.getQuiz().getTitle())).count());
        }).sorted((r1, r2) -> r2.getFinishTime().compareTo(r1.getFinishTime())).collect(Collectors.toList());

        return historyList;
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        return quizService.findById(id);
    }

    @Override
    public Long findRankByScore(BigDecimal score) {
        return resultRepository.findRankByScore(score);
    }

    @Override
    public ResultHistoryDTO finDetailHistory(Long id) {
        Optional<Result> result = resultRepository.findById(id);
        if (!result.isPresent()) {
            throw new RuntimeException("Result not found");
        }
        Long rank = findRankByScore(result.get().getScore());
        if (rank == null) {
            throw new RuntimeException("khong ton tai xep hang");
        }
        ResultHistoryDTO resultHistoryDTO = new ResultHistoryDTO();
        resultHistoryDTO.setId(result.get().getId());
        resultHistoryDTO.setQuizName(result.get().getQuiz().getTitle());
        resultHistoryDTO.setSubmitTime(result.get().getFinishTime());
        resultHistoryDTO.setScore(result.get().getScore());
        resultHistoryDTO.setCorrectAnswers(result.get().getCorrectAnswers());
        resultHistoryDTO.setIncorrectAnswers(result.get().getIncorrectAnswers());
        resultHistoryDTO.setRank(rank);
        return resultHistoryDTO;
    }

    @Override
    public List<Result> findByUserId(Long userId, boolean status) {
        return resultRepository.findByUserIdAndStatus(userId, status);
    }

    @Override
    public List<HistoryStudentExam> findStudentExamByUserId(Long userId) {
        List<Result> results = resultRepository.findByUserIdAndStatus(userId, true);
        List<HistoryStudentExam> resultHistoryList = new ArrayList<>();
        for (Result result : results) {
            if (result.getStartTime() != null && result.getFinishTime() != null) {
                HistoryStudentExam history = new HistoryStudentExam();
                history.setId(result.getUser().getId());

                Duration examDuration = Duration.between(result.getStartTime(), result.getFinishTime());
                String formattedDuration = formatDuration(examDuration);

                history.setTime(formattedDuration);
                history.setUserName(result.getUser().getName());
                history.setScore(result.getScore());
                history.setAnswerCorrect(result.getCorrectAnswers());
                history.setAnswerTotal(result.getCorrectAnswers() + result.getIncorrectAnswers());
                resultHistoryList.add(history);
            }
        }
        return resultHistoryList;
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return String.format("%d giờ %d phút %d giây", hours, minutes, seconds);
    }
}
