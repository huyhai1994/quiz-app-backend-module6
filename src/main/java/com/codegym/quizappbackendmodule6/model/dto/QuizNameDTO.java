package com.codegym.quizappbackendmodule6.model.dto;

public class QuizNameDTO {
    private String name;

    public QuizNameDTO(String name) {
        this.name = name;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}