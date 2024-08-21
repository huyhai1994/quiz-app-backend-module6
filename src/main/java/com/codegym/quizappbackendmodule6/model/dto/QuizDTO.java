package com.codegym.quizappbackendmodule6.model.dto;

import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;


public interface QuizDTO {
    Long getQuizzesId();
    String getQuizzesTitle();
    String getQuizzesDescription();
    String getUsersName();
    String getUserEmail();
    LocalDateTime getQuizzesTimeCreate();
    String getQuizzesByCategory();
}
