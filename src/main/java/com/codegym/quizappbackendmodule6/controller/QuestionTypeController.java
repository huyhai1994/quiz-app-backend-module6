package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.QuestionType;
import com.codegym.quizappbackendmodule6.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/question-type")
@RequiredArgsConstructor
public class QuestionTypeController {

    private final QuestionTypeService questionTypeService;

    @GetMapping("/get-all")
    public List<QuestionType> getAllQuestionTypes() {
        return questionTypeService.findAll();
    }
}