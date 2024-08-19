package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.dto.AddQuestionIntoQuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionResponse;
import com.codegym.quizappbackendmodule6.model.dto.QuestionStudentDTO;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionDTO> findAllQuestionDetails();

    Question save(Question question);

    Optional<Question> findById(Long questionId);

    void deleteById(Long id);

    List<QuestionDTO> findQuestionsByCategoryAndName(String searchTerm);

    List<QuestionDTO> findAllTeacherQuestionDetails(Long userId);

    List<AddQuestionIntoQuizDTO> addQuestionsByCategoryNameAndUserId(String categoryName, Long userId);

    List<QuestionStudentDTO> getQuestionDTOsByQuizId(Long quizId);

    List<QuestionResponse> findAllByQuizId(Long quizId);

    void deleteQuestionById(Long questionId);
    
    boolean isQuestionInAnyQuiz(Long questionId);
}
