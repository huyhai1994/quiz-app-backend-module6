package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.dto.OptionStudentDTO;
import com.codegym.quizappbackendmodule6.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/option")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    @PostMapping("/create/{questionId}")
    public ResponseEntity<List<Option>> createOptions(@RequestBody List<Option> options, @PathVariable Long questionId) {
        List<Option> createdOptions = options.stream()
                .map(option -> optionService.saveOption(option, questionId))
                .collect(Collectors.toList());
        return ResponseEntity.ok(createdOptions);
    }

    @GetMapping("/question")
    public ResponseEntity<List<OptionStudentDTO>> getOptionsByQuestionId(@RequestParam Long questionId) {
        List<OptionStudentDTO> options = optionService.findOptionsByQuestionId(questionId);
        return ResponseEntity.ok(options);
    }
}
