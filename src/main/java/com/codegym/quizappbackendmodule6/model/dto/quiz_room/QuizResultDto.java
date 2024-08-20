package com.codegym.quizappbackendmodule6.model.dto.quiz_room;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultDto {
    private Long id;
    private String userName;
    private LocalDateTime finishTime;
    private BigDecimal score;
    private Long correctAnswers;
    private Long incorrectAnswers;
}
