package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.DTO.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import com.codegym.quizappbackendmodule6.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping("/list")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> answer = questionService.findAllQuestionDetails();
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/create")
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.save(question);
        return ResponseEntity.ok(createdQuestion);
    }
}
