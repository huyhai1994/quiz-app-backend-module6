package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.QuizCategory;
import com.codegym.quizappbackendmodule6.service.QuizCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz-categories")
@AllArgsConstructor
public class QuizCategoryController {
    private final QuizCategoryService quizCategoryService;

    @GetMapping
    public ResponseEntity<List<QuizCategory>> getAllQuizCategories() {
        List<QuizCategory> categories = quizCategoryService.findAll();
        return ResponseEntity.ok(categories);
    }

}