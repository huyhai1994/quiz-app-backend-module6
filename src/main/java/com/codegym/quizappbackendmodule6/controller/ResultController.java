package com.codegym.quizappbackendmodule6.controller;


import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.Result;
import com.codegym.quizappbackendmodule6.model.UserAnswer;
import com.codegym.quizappbackendmodule6.model.dto.QuizHistoryDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizResultDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserAnswerDto;
import com.codegym.quizappbackendmodule6.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/result")
@RequiredArgsConstructor
public class ResultController {
    private final ResultService resultService;

    @PostMapping("/start/{userId}/{quizId}")
    public ResponseEntity<Result> start(@PathVariable Long userId, @PathVariable Long quizId){
        return ResponseEntity.ok(resultService.startQuiz(userId,quizId));
    }

    @PostMapping("/end/{resultId}")
    public ResponseEntity<Result> start(@PathVariable Long resultId, @RequestBody List<UserAnswerDto> userAnswers){
        return ResponseEntity.ok(resultService.endQuiz(resultId, userAnswers));
    }

    //  kết qua bài vừa thi
    @GetMapping("/{resultId}")
    public ResponseEntity<QuizResultDTO> getQuizResultsByUserId(@PathVariable Long resultId) {
        return ResponseEntity.ok(resultService.getQuizResultById(resultId));
    }

    // lịch sử thi
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<QuizHistoryDTO>> getQuizHistoryByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(resultService.getQuizHistoryByUserId(userId));
    }

    @GetMapping("/quizzes/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Optional<Quiz> quiz = resultService.getQuizById(id);
        if (quiz.isPresent()) {
            return ResponseEntity.ok(quiz.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
