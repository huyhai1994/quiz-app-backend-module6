package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.QuizCategory;
import com.codegym.quizappbackendmodule6.repository.QuizCategoryRepository;
import com.codegym.quizappbackendmodule6.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {

    @Autowired
    private QuizCategoryRepository quizCategoryRepository;

    @Override
    public List<QuizCategory> findAll() {
        return quizCategoryRepository.findAll();
    }

    @Override
    public Optional<QuizCategory> findById(Long id) {
        return quizCategoryRepository.findById(id);
    }
}
