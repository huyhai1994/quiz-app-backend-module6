package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
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
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<QuizDTO>> getQuizList() {
        List<QuizDTO> quizList = quizService.findQuizDetails();
        return ResponseEntity.ok(quizList);
    }

    @GetMapping("/list-teacher/{userId}")
    public ResponseEntity<List<QuizTeacherDTO>> findTeacherQuizDetails(@PathVariable Long userId) {
        List<QuizTeacherDTO> quizList = quizService.findTeacherQuizDetails(userId);
        return ResponseEntity.ok(quizList);
    }

    @PostMapping("/create")
    public ResponseEntity<Quiz> createQuiz(@RequestParam Long userId, @RequestBody Quiz quiz) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        quiz.setCreatedBy(user);
        Quiz quizNew = quizService.createQuiz(quiz);
        return ResponseEntity.ok(quizNew);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuiz(@Valid @PathVariable Long id, @RequestBody Quiz quiz, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bạn đã nhập sai trường");
        }
        Quiz updatedQuiz = quizService.updateQuiz(quiz, id);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findById(id)
               .orElseThrow(() -> new RuntimeException("Quiz not found"));
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/find-by-name/{name}")
    public ResponseEntity<List<Quiz>> findQuizByName(@PathVariable String name) {
        List<Quiz> quizList = quizService.findQuizByName(name);
        return ResponseEntity.ok(quizList);
    }
}
