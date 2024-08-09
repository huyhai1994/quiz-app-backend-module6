package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Option;
import com.codegym.quizappbackendmodule6.service.OptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
