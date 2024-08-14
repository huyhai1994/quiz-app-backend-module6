package com.codegym.quizappbackendmodule6.model.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizNameDTO {
    // Getter and Setter
    private String title;

    public QuizNameDTO(String title) {
        this.title = title;
    }

}