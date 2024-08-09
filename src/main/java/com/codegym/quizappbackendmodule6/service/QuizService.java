package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.DTO.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.DTO.QuizDTO;
import com.codegym.quizappbackendmodule6.model.DTO.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.model.Quiz;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    List<QuizDTO> findQuizDetails();
    List<Quiz> getQuizByCategory(String title);
    Optional<Quiz> getQuizById(Long id);
    Quiz createQuiz(Quiz quiz);
    void deleteQuiz(Long id);
    Quiz updateQuiz(Quiz quiz , Long id);

    Optional<Quiz> findById(Long quizId);

    List<QuizTeacherDTO> findTeacherQuizDetails(Long userId);


}
