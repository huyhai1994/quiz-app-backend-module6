package com.codegym.quizappbackendmodule6.service;


import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.model.Quiz;


import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    List<QuizHotDTO> findTopQuizzesByResultCount();

    List<QuizTeacherHistory> findAllQuizTeacherHistory(Principal principal);

}
