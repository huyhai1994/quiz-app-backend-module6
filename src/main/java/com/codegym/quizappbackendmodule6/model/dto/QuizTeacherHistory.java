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
public class QuizTeacherHistory {
    private Long id;
    private String name;
    private int quantity;
    private String difficulty;
    private LocalDateTime finishTime;
    private String score;
    private Long quantityExam;
}
