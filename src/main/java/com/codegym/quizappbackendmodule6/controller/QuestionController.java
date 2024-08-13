package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.dto.AddQuestionIntoQuizDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.dto.QuestionDTO;
import com.codegym.quizappbackendmodule6.model.dto.QuestionResponse;
import com.codegym.quizappbackendmodule6.model.dto.QuestionStudentDTO;
import com.codegym.quizappbackendmodule6.service.QuestionService;
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
    public ResponseEntity<List<AddQuestionIntoQuizDTO>> getQuestionsByCategoryName(@PathVariable String categoryName , @PathVariable Long userId) {
        List<AddQuestionIntoQuizDTO> questions = questionService.addQuestionsByCategoryNameAndUserId(categoryName , userId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionDTOsByQuizId(@PathVariable Long quizId) {
        List<QuestionResponse> questions = questionService.findAllByQuizId(quizId);
        return ResponseEntity.ok(questions);
    }
}
