package com.codegym.quizappbackendmodule6.service;


import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.model.Quiz;


import java.util.List;
import java.util.Optional;

public interface QuizService {

    List<QuizDTO> findQuizDetails();
    List<Quiz> getQuizByCategory(String title);
    Optional<Quiz> getQuizById(Long id);
    Quiz createQuiz(Quiz quiz);
    void deleteQuiz(Long id);
    Quiz updateQuiz(Long id, UpdateQuizRequestDto updateQuizRequestDto);
    Optional<Quiz> findById(Long quizId);
    List<QuizTeacherDTO> findTeacherQuizDetails(Long userId);

    Quiz addQuestionsToQuiz(Long quizId, List<Long> questionIds);

    List<QuizStudentDTO> getAllQuizzes();
    List<QuizNameDTO> getAllQuizNames();
}
