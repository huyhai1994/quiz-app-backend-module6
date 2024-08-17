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
    private String userName;
    private String userEmail;
    private Long attemptCount;
}
