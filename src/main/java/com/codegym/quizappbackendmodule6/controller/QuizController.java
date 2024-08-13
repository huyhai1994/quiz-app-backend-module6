package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.service.QuizService;
import com.codegym.quizappbackendmodule6.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quizzes")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getQuizList() {
        List<QuizDTO> quizList = quizService.findQuizDetails();
        return ResponseEntity.ok(quizList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<QuizTeacherDTO>> findTeacherQuizDetails(@PathVariable Long userId) {
        List<QuizTeacherDTO> quizList = quizService.findTeacherQuizDetails(userId);
        return ResponseEntity.ok(quizList);
    }

    @GetMapping("/exam")
    public ResponseEntity<List<QuizStudentDTO>> getAllQuizzes() {
        List<QuizStudentDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findById(id)
               .orElseThrow(() -> new RuntimeException("Quiz not found"));
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<List<QuizDTO>> getQuizByCategoryId(@PathVariable String title) {
        List<QuizDTO> quizList = quizService.getQuizByCategory(title);
        return ResponseEntity.ok(quizList);
    }

    @GetMapping
    public ResponseEntity<String> searchQuizzes(@RequestParam(required = false) String title,
                                                @RequestParam(required = false) String category) {
        if (title != null) {
            return ResponseEntity.ok(String.valueOf(quizService.findByTitle(title)));
        } else if (category != null) {
            return ResponseEntity.ok(quizService.getQuizByCategory(category).toString());
        } else {
            return ResponseEntity.badRequest().body("Please provide either 'title' or 'category' parameter.");
        }
    }

}
