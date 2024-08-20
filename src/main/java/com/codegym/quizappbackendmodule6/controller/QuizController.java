package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.Quiz;
import com.codegym.quizappbackendmodule6.model.dto.QuizTimeDTO;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.*;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private final QuizCategoryService quizCategoryService;
    private final QuizRoomService quizRoomService;

    @GetMapping
    public ResponseEntity<List<QuizDTO>> getQuizList() {
        List<QuizDTO> quizList = quizService.findQuizDetails();
        return ResponseEntity.ok(quizList);
    }

    @PostMapping
    public ResponseEntity<Quiz> createQuiz(@Valid @RequestBody QuizRequestDTO quizRequestDTO, Principal principal) {

        User user = userService.getUserByEmail(principal.getName());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Set<Question> questions = quizRequestDTO.getQuestionIds().stream().map(questionId -> questionService.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found: " + questionId))).collect(Collectors.toSet());

        QuizCategory quizCategory = quizCategoryService.findById(quizRequestDTO.getQuizCategoryId()).orElseThrow(() -> new RuntimeException("QuizCategory not found: " + quizRequestDTO.getQuizCategoryId()));

        Quiz quiz = new Quiz();
        quiz.setTitle(quizRequestDTO.getTitle());
        quiz.setDescription(quizRequestDTO.getDescription());
        quiz.setQuizTime(Long.valueOf(quizRequestDTO.getQuizTime()));
        quiz.setQuantity(quizRequestDTO.getQuantity());
        quiz.setPassingScore(quizRequestDTO.getPassingScore());
        quiz.setQuestions(questions);
        quiz.setTimeCreate(quizRequestDTO.getTimeCreated());
        quiz.setDifficulty(quizRequestDTO.getDifficulty());
        quiz.setQuizCategory(quizCategory);
        quiz.setCreatedBy(user);

        Quiz quizNew = quizService.createQuiz(quiz);
        return ResponseEntity.ok(quizNew);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @Valid @RequestBody UpdateQuizRequestDto updateQuizRequestDto) {
        Quiz updatedQuiz = quizService.updateQuiz(id, updateQuizRequestDto);
        return ResponseEntity.ok(updatedQuiz);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        Quiz quiz = quizService.findById(id).orElseThrow(() -> new RuntimeException("Quiz not found"));
        return ResponseEntity.ok(quiz);
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchQuizByNameOrCategory(@RequestParam(required = false) String title, @RequestParam(required = false) String categoryTitle) {
        if (title != null) {
            return ResponseEntity.ok(String.valueOf(quizService.findByTitle(title)));
        } else if (categoryTitle != null) {
            return ResponseEntity.ok(quizService.getQuizByCategory(categoryTitle).toString());
        } else {
            return ResponseEntity.badRequest().body("Please provide either 'title' or 'category' parameter.");
        }
    }

    @GetMapping("/titles")
    public ResponseEntity<List<QuizNameDTO>> getAllQuizNames() {
        List<QuizNameDTO> quizNames = quizService.getAllQuizNames();
        return ResponseEntity.ok(quizNames);
    }

    @GetMapping("/list-teacher/{userId}")
    public ResponseEntity<List<QuizTeacherDTO>> findTeacherQuizDetails(@PathVariable Long userId) {
        List<QuizTeacherDTO> quizList = quizService.findTeacherQuizDetails(userId);
        return ResponseEntity.ok(quizList);
    }

    @GetMapping("/exam")
    public ResponseEntity<List<QuizStudentDTO>> getAllQuizzes() {
        List<QuizStudentDTO> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{quizId}/time")
    public ResponseEntity<QuizTimeDTO> getQuizTimeById(@PathVariable Long quizId) {
        QuizTimeDTO quizTimeDTO = quizService.getQuizTimeById(quizId);
        return ResponseEntity.ok(quizTimeDTO);
    }

    @GetMapping("/quizzes/top")
    public List<QuizHotDTO> getTopQuizzes() {
        return quizService.findTopQuizzesByResultCount(true);
    }

    @GetMapping("/{quizId}/user-info")
    public ResponseEntity<List<QuizTeacherHistory>> getHistoryUserByQuizId(@PathVariable Long quizId) {
        List<QuizTeacherHistory> historyList = quizService.getHistoryUserByQuizId(quizId,true);
        return ResponseEntity.ok(historyList);
    }
}
