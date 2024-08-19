package com.codegym.quizappbackendmodule6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class HistoryStudentExam {
    private Long id;
    private String time;
    private String userName;
    private BigDecimal score;
    private Long answerCorrect;
    private Long answerTotal;
}
