package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.QuizCategory;
import com.codegym.quizappbackendmodule6.repository.QuizCategoryRepository;
import com.codegym.quizappbackendmodule6.service.QuizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizCategoryServiceImpl implements QuizCategoryService {

    @Autowired
    private QuizCategoryRepository quizCategoryRepository;

    @Override
    public List<QuizCategory> findAll() {
        return quizCategoryRepository.findAll();
    }


}
