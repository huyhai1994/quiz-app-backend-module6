package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.QuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizRequestDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizStudentDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuizTeacherDTO;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import com.codegym.quizappbackendmodule6.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
@RequiredArgsConstructor
public class QuizController {
    private final QuizService quizService;
    private final UserService userService;
    private final QuestionService questionService;

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
    public ResponseEntity<Quiz> createQuiz(@RequestParam Long userId, @Valid @RequestBody QuizRequestDTO quizRequestDTO) {
        User user = userService.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Set<Question> questions = quizRequestDTO.getQuestionIds().stream().map(questionId -> questionService.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found: " + questionId))).collect(Collectors.toSet());

        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequestDTO.getTitle());
        quiz.setDescription(quizRequestDTO.getDescription());
        quiz.setQuizTime(Long.valueOf(quizRequestDTO.getQuizTime()));
        quiz.setQuantity(quizRequestDTO.getQuantity());
        quiz.setPassingScore(quizRequestDTO.getPassingScore());
        quiz.setQuestions(questions);
        quiz.setTimeCreate(quizRequestDTO.getTimeCreated());
        quiz.setCreatedBy(user);

        Quiz quizNew = quizService.createQuiz(quiz);
        return ResponseEntity.ok(quizNew);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateQuiz(@Valid @PathVariable Long id, @Valid @RequestBody QuizRequestDTO quizRequestDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bạn đã nhập sai trường");
        }

        Set<Question> questions = quizRequestDTO.getQuestionIds().stream().map(questionId -> questionService.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found: " + questionId))).collect(Collectors.toSet());

        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequestDTO.getTitle());
        quiz.setDescription(quizRequestDTO.getDescription());
        quiz.setQuizTime(Long.valueOf(quizRequestDTO.getQuizTime()));
        quiz.setQuantity(quizRequestDTO.getQuantity());
        quiz.setPassingScore(quizRequestDTO.getPassingScore());
        quiz.setQuestions(questions);
        quiz.setTimeCreate(quizRequestDTO.getTimeCreated());

        Quiz updatedQuiz = quizService.updateQuiz(quiz, id);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exam")
    public ResponseEntity<List<QuizStudentDTO>> getAllQuizzes() {
        List<QuizStudentDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }
}