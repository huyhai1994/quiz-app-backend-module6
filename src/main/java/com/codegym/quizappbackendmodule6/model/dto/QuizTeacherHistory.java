package com.codegym.quizappbackendmodule6.model.dto;

import com.codegym.quizappbackendmodule6.model.Quiz;
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
public class QuizTeacherHistory {
    private Long id;
    private String name;
    private int quantity;
    private Quiz.Difficulty difficulty;
    private LocalDateTime finishTime;
    private BigDecimal score;
    private Long quantityExam;
}
