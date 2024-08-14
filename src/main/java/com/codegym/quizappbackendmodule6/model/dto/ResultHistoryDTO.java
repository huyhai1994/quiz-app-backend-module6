package com.codegym.quizappbackendmodule6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultHistoryDTO {
    private Long id;
    private String quizName;
    private LocalDateTime submitTime;
    private Long score;
    private Long correctAnswers;
    private Long incorrectAnswers;
    private Long rank;
}
