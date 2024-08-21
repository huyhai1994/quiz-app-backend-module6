package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.Question;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.model.dto.question.request.CreateQuestionRequestDTO;
import com.codegym.quizappbackendmodule6.model.dto.question.response.QuestionDetailsDTO;
import com.codegym.quizappbackendmodule6.service.OptionService;
import com.codegym.quizappbackendmodule6.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    private final OptionService optionService;

    @GetMapping("/list")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> answer = questionService.findAllQuestionDetails();
        return ResponseEntity.ok(answer);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        // Create and save the question
        Question question = new Question();
        question.setQuestionText(createQuestionRequestDTO.getQuestionText());
        question.setQuestionType(createQuestionRequestDTO.getQuestionType());
        question.setDifficulty(createQuestionRequestDTO.getDifficulty());
        question.setCategory(createQuestionRequestDTO.getCategory());
        question.setCreatedBy(createQuestionRequestDTO.getCreatedBy());
        Question savedQuestion = questionService.save(question);

        // Create and save the options
        List<Option> options = createQuestionRequestDTO.getOptions().stream().map(optionDTO -> {
            Option option = new Option();
            option.setOptionText(optionDTO.getOptionText());
            option.setIsCorrect(optionDTO.getIsCorrect());
            option.setQuestion(savedQuestion);
            return option;
        }).collect(Collectors.toList());
        optionService.saveOption(options);

        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateQuestion(@PathVariable Long id, @RequestBody CreateQuestionRequestDTO createQuestionRequestDTO) {
        // Find the existing question
        Optional<Question> existingQuestionOpt = questionService.findById(id);
        if (existingQuestionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Question existingQuestion = existingQuestionOpt.get();

        // Check if the question is part of any quiz
        if (questionService.isQuestionInAnyQuiz(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Bạn không thể cập nhập câu " + "hỏi đã nằm trong bài thi");
        }

        // Delete existing options related to the question
        List<OptionStudentDTO> existingOptions = optionService.findOptionsByQuestionId(id);
        for (OptionStudentDTO option : existingOptions) {
            optionService.deleteById(option.getId());
        }

        // Update the question details
        existingQuestion.setQuestionText(createQuestionRequestDTO.getQuestionText());
        existingQuestion.setQuestionType(createQuestionRequestDTO.getQuestionType());
        existingQuestion.setDifficulty(createQuestionRequestDTO.getDifficulty());
        existingQuestion.setCategory(createQuestionRequestDTO.getCategory());
        existingQuestion.setCreatedBy(createQuestionRequestDTO.getCreatedBy());
        Question updatedQuestion = questionService.save(existingQuestion);

        // Create and save the new options
        List<Option> newOptions = createQuestionRequestDTO.getOptions().stream().map(optionDTO -> {
            Option option = new Option();
            option.setOptionText(optionDTO.getOptionText());
            option.setIsCorrect(optionDTO.getIsCorrect());
            option.setQuestion(updatedQuestion);
            return option;
        }).collect(Collectors.toList());
        optionService.saveOption(newOptions);

        return ResponseEntity.ok(updatedQuestion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateQuestionRequestDTO> getQuestionById(@PathVariable Long id) {
        Optional<Question> questionOpt = questionService.findById(id);
        if (questionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Question question = questionOpt.get();

        // Map Question to CreateQuestionRequestDTO
        CreateQuestionRequestDTO questionDTO = new CreateQuestionRequestDTO();
        questionDTO.setQuestionText(question.getQuestionText());
        questionDTO.setQuestionType(question.getQuestionType());
        questionDTO.setDifficulty(question.getDifficulty());
        questionDTO.setCategory(question.getCategory());
        questionDTO.setCreatedBy(question.getCreatedBy());

        // Map Options to OptionDTO
        List<CreateQuestionRequestDTO.OptionDTO> optionDTOs = question.getOptions().stream().map(option -> {
            CreateQuestionRequestDTO.OptionDTO optionDTO = new CreateQuestionRequestDTO.OptionDTO();
            optionDTO.setOptionText(option.getOptionText());
            // Exclude isCorrect field
            return optionDTO;
        }).collect(Collectors.toList());
        questionDTO.setOptions(optionDTOs);

        return ResponseEntity.ok(questionDTO);
    }

    @GetMapping("/search/questions")
    public ResponseEntity<List<QuestionDTO>> findQuestionsBySearchTerm(@RequestParam(required = false) String searchTerm) {
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

    @GetMapping("/specific-question/{id}")
    public ResponseEntity<QuestionDetailsDTO> getQuestionDetail(@PathVariable Long id) {
        Optional<Question> questionOptional = questionService.findById(id);
        if (questionOptional.isPresent()) {
            Question question = questionOptional.get();
            Set<OptionDTO> options = question.getOptions().stream().map(option -> new OptionDTO(option.getId(), option.getOptionText())).collect(Collectors.toSet());

            QuestionDetailsDTO questionDetailsDTO = QuestionDetailsDTO.builder().id(question.getId()).questionText(question.getQuestionText()).options(options).typeName(question.getQuestionType().getTypeName()).difficulty(String.valueOf(question.getDifficulty())).category(question.getCategory().getName()).createdBy(question.getCreatedBy().getName()).timeCreate(question.getTimeCreate()).build();

            return ResponseEntity.ok(questionDetailsDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}