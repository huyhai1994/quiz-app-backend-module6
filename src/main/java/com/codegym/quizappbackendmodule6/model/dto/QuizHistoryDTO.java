package com.codegym.quizappbackendmodule6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizHistoryDTO {
    private Long id;
    private String quizName;
    private LocalDateTime finishTime;
    private String durationMinutes;
    private BigDecimal score;
    private Integer attemptNumber;
}
