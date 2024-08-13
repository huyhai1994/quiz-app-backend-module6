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
public class QuizHistoryDTO {
    private String quizName;
    private LocalDateTime finishTime;
    private long durationMinutes;
    private Long score;
    private Integer attemptNumber;
}
