package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.QuizTimeDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizNameDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.repository.QuestionRepository;
import com.codegym.quizappbackendmodule6.repository.QuizRepository;
import com.codegym.quizappbackendmodule6.service.QuizService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public Quiz updateQuiz(Quiz quiz, Long id) {
        if (!quizRepository.existsById(id)) {
            throw new RuntimeException("Quiz không tồn tại với ID: " + id);
        }
        quiz.setId(id);
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

}