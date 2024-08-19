package com.codegym.quizappbackendmodule6.service;


import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.QuizTimeDTO;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.model.dto.question.request.QuizByCategoryDTO;

import java.util.List;
import java.util.Optional;

public interface QuizService {
    List<QuizDTO> findQuizDetails();

    Optional<Quiz> getQuizById(Long id);

    Quiz createQuiz(Quiz quiz);

    void deleteQuiz(Long id);

    Quiz updateQuiz(Long id, UpdateQuizRequestDto updateQuizRequestDTO);

    Optional<Quiz> findById(Long quizId);

    List<QuizTeacherDTO> findTeacherQuizDetails(Long userId);

    Quiz addQuestionsToQuiz(Long quizId, List<Long> questionIds);

    List<QuizStudentDTO> getAllQuizzes();

    List<QuizNameDTO> getAllQuizNames();

    List<Quiz> findByTitle(String title);

    List<QuizHotDTO> findTopQuizzesByResultCount();

    QuizTimeDTO getQuizTimeById(Long quizId);

//   List<QuizByCategoryDTO> getQuizByQuizCategory(String category);
    List<QuizTeacherHistory> getHistoryUserByQuizId(Long quizID);
}
