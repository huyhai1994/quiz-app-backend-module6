package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.model.dto.OptionStudentDTO;
import com.codegym.quizappbackendmodule6.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/option")
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;
    @PostMapping("/create")
    public ResponseEntity<Option> createOption(@RequestBody Option option, @RequestParam Long questionId){
        Option createOption = optionService.saveOption(option,questionId);
        return ResponseEntity.ok(createOption);
    }

    @GetMapping("/question")
    public ResponseEntity<List<OptionStudentDTO>> getOptionsByQuestionId(@RequestParam Long questionId) {
        List<OptionStudentDTO> options = optionService.findOptionsByQuestionId(questionId);
        return ResponseEntity.ok(options);
    }
}
