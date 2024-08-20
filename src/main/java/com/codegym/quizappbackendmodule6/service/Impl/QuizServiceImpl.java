package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.QuizTimeDTO;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.repository.QuestionRepository;
import com.codegym.quizappbackendmodule6.repository.QuizRepository;
import com.codegym.quizappbackendmodule6.service.QuizService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final UserService userService;
    private final QuestionRepository questionRepository;

    @Override
    public List<QuizDTO> findQuizDetails() {
        return quizRepository.findQuizDetails();
    }

    @Override
    public List<QuizDTO> getQuizByCategory(String title) {
        return quizRepository.getQuizzesByCategory();
    }

    @Override
    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    @Override
    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long id) {
        if (quizRepository.existsById(id)) {
            quizRepository.deleteById(id);
        } else {
            throw new RuntimeException("Quiz không tồn tại với ID: " + id);
        }
    }

    @Override
    public Quiz updateQuiz(Long id, UpdateQuizRequestDto updateQuizRequestDTO) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz không tồn tại với Id: " + id));

        quiz.setTitle(updateQuizRequestDTO.getTitle());
        quiz.setDescription(updateQuizRequestDTO.getDescription());
        quiz.setQuizTime(updateQuizRequestDTO.getQuizTime());
        quiz.setQuantity(updateQuizRequestDTO.getQuantity());
        quiz.setPassingScore(updateQuizRequestDTO.getPassingScore());

        if (updateQuizRequestDTO.getQuestionIds() != null && !updateQuizRequestDTO.getQuestionIds().isEmpty()) {
            Set<Question> questions = new HashSet<>(questionRepository.findAllById(updateQuizRequestDTO.getQuestionIds()));
            quiz.setQuestions(questions);
        }
        return quizRepository.save(quiz);
    }

    @Override
    public Optional<Quiz> findById(Long quizId) {
        return quizRepository.findById(quizId);
    }

    @Override
    public List<QuizTeacherDTO> findTeacherQuizDetails(Long userId) {
        return quizRepository.findTeacherQuizDetails(userId);
    }

    @Override
    public Quiz addQuestionsToQuiz(Long quizId, List<Long> questionIds) {
        Quiz quiz = quizRepository.findById(quizId).orElse(null);
        if (quiz != null) {
            // Tìm các câu hỏi từ danh sách ID
            List<Question> questionList = questionRepository.findAllById(questionIds);
            // Chuyển đổi danh sách câu hỏi thành Set để đảm bảo tính duy nhất
            Set<Question> questions = new HashSet<>(questionList);
            // Thêm các câu hỏi vào quiz
            quiz.getQuestions().addAll(questions);
            // Lưu quiz đã cập nhật
            return quizRepository.save(quiz);
        }
        return null;
    }

    @Override
    public List<QuizStudentDTO> getAllQuizzes() {
        return quizRepository.findAllQuizzesWithDTO();
    }

    @Override
    public List<QuizNameDTO> getAllQuizNames() {
        return quizRepository.findAllQuizNames();
    }

    @Override
    public List<Quiz> findByTitle(String title) {
        return quizRepository.findByTitle(title);
    }

    @Override
    public QuizTimeDTO getQuizTimeById(Long quizId) {
        return quizRepository.findQuizTimeById(quizId);
    }

//    @Override
//    public List<QuizByCategoryDTO> getQuizByQuizCategory(String category) {
//        List<Quiz> quizzes = quizRepository.findByQuizCategory(category);
//
//        return quizzes.stream()
//                .map(quiz -> new QuizByCategoryDTO(quiz.getId(), quiz.getTitle(), quiz.getDescription(), quiz.getCategory()))
//                .collect(Collectors.toList());
//    }

    @Override
    public List<QuizHotDTO> findTopQuizzesByResultCount(Boolean status) {
        List<QuizHotDTO> results = quizRepository.findTopQuizzesByResultCount(status);
        return results.stream()
                .limit(5)
                .map(result -> new QuizHotDTO(
                        result.getId(),
                        result.getTitle(),
                        result.getResultCount()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizTeacherHistory> getHistoryUserByQuizId(Long quizID , Boolean status) {
        return quizRepository.getHistoryUserByQuizId(quizID ,status);
    }
}