package com.codegym.quizappbackendmodule6.service;


import com.codegym.quizappbackendmodule6.model.dto.QuestionStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.model.Quiz;


import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuizService {
    List<QuizDTO> findQuizDetails();
    Optional<Quiz> getQuizById(Long id);
    Quiz createQuiz(Quiz quiz);
    void deleteQuiz(Long id);
    Quiz updateQuiz(Quiz quiz , Long id);

    Optional<Quiz> findById(Long quizId);

    List<QuizTeacherDTO> findTeacherQuizDetails(Long userId);

    Quiz addQuestionsToQuiz(Long quizId, List<Long> questionIds);

    List<QuizStudentDTO> getAllQuizzes();

    Optional<Quiz> findByTitle(String title);



}
