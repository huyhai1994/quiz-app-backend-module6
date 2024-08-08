package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.service.UserAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/user-answer")
@RequiredArgsConstructor
public class UserAnswerController {
    private final UserAnswerService userAnswerService;
}
