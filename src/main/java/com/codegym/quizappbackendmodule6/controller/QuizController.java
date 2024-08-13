package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/search")
    public ResponseEntity<String> searchQuizByNameOrCategory(@RequestParam(required = false) String title,
                                                @RequestParam(required = false) String category_title) {
        if (title != null) {
            return ResponseEntity.ok(String.valueOf(quizService.findByTitle(title)));
        } else if (category_title != null) {
            return ResponseEntity.ok(quizService.getQuizByCategory(category_title).toString());
        } else {
            return ResponseEntity.badRequest().body("Please provide either 'title' or 'category' parameter.");
        }
    }

}
