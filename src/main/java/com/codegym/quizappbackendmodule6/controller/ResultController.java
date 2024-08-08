//package com.codegym.quizappbackendmodule6.controller;
//
//
//import com.codegym.quizappbackendmodule6.model.Result;
//import com.codegym.quizappbackendmodule6.service.ResultService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/result")
//@RequiredArgsConstructor
//public class ResultController {
//    private final ResultService resultService;
//
//    @PostMapping("/start/{userId}/{quizId}")
//    public ResponseEntity<Result> start(@PathVariable Long userId, @PathVariable Long quizId) {
//        return ResponseEntity.ok(resultService.startQuiz(userId, quizId));
//    }
//
//    @PostMapping("/end/{resultId}")
//    public ResponseEntity<Result> start(@PathVariable Long resultId) {
//        return ResponseEntity.ok(resultService.endQuiz(resultId));
//    }
//}
