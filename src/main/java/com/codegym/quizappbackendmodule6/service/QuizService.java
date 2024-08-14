package com.codegym.quizappbackendmodule6.service;


import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.QuizTimeDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizNameDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    List<QuizDTO> findQuizDetails();

    List<QuizDTO> getQuizByCategory(String title);

    Optional<Quiz> getQuizById(Long id);

    Quiz createQuiz(Quiz quiz);

    void deleteQuiz(Long id);

    Quiz updateQuiz(Quiz quiz, Long id);

    Optional<Quiz> findById(Long quizId);

    List<QuizTeacherDTO> findTeacherQuizDetails(Long userId);

    Quiz addQuestionsToQuiz(Long quizId, List<Long> questionIds);

    List<QuizStudentDTO> getAllQuizzes();

    List<QuizNameDTO> getAllQuizNames();

    List<Quiz> findByTitle(String title);

    public QuizTimeDTO getQuizTimeById(Long quizId);

}
