package com.codegym.quizappbackendmodule6.model.dto;

import com.codegym.quizappbackendmodule6.model.Quiz;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class QuizRequestDTO {
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Integer quizTime;

    @NotNull
    private Integer quantity;

    @NotNull
    private Integer passingScore;

    @NotNull
    private List<Long> questionIds;

    @NotNull
    private Long quizCategoryId;

    @NotNull
    private Quiz.Difficulty difficulty;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private LocalDateTime timeCreated;

}