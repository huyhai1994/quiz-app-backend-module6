package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.QuizCategory;

import java.util.List;
import java.util.Optional;

public interface QuizCategoryService {
    List<QuizCategory> findAll();

    Optional<QuizCategory> findById(Long quizCategoriesId);
}
