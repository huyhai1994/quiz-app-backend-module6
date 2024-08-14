package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.dto.AddQuestionIntoQuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionResponse;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question createdQuestion = questionService.save(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question question) {
        question.setId(id);

        Question updatedQuestion = questionService.save(question);
        return ResponseEntity.ok(updatedQuestion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Question>> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.findById(id);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/search/questions")
    public ResponseEntity<List<QuestionDTO>> findQuestionsBySearchTerm(
            @RequestParam(required = false) String searchTerm) {
        List<QuestionDTO> questions = questionService.findQuestionsByCategoryAndName(searchTerm);
        return ResponseEntity.ok(questions);
    }


    @GetMapping("/list-teacher/{userId}")
    public ResponseEntity<List<QuestionDTO>> findAllTeacherQuestionDetails(@PathVariable Long userId) {
        List<QuestionDTO> answer = questionService.findAllTeacherQuestionDetails(userId);
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<AddQuestionIntoQuizDTO>> getQuestionsByCategoryName(@PathVariable String categoryName, @PathVariable Long userId) {
        List<AddQuestionIntoQuizDTO> questions = questionService.addQuestionsByCategoryNameAndUserId(categoryName, userId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionDTOsByQuizId(@PathVariable Long quizId) {
        List<QuestionResponse> questions = questionService.findAllByQuizId(quizId);
        return ResponseEntity.ok(questions);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.noContent().build();
    }
}
