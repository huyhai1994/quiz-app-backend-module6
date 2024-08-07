package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.QuestionType;

import java.util.Optional;

public interface QuestionTypeService {
    Optional<QuestionType> findById(Long id);

}
