package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.DTO.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> findAllQuestionDetails();
    Question save(Question question);
}
