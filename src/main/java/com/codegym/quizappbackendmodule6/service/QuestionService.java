package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    List<QuestionDTO> findAllQuestionDetails();
    Question save(Question question);

    Optional<Question> findById(Long questionId);
}
