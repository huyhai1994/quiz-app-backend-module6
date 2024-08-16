package com.codegym.quizappbackendmodule6.model.dto;

import java.time.LocalDateTime;

public interface QuizTeacherDTO {
    Long getQuizzesId();
    String getQuizzesTitle();
    String getQuizzesDescription();
    LocalDateTime getQuizzesTimeCreate();
    Long getQuizTime();
    int getQuantity();
    int getPassingScore();
    String getDifficulty();
}
