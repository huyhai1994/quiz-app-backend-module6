package com.codegym.quizappbackendmodule6.model.dto;

import java.time.LocalDateTime;

public interface QuestionDTO {
    Long getQuestionId();

    String getQuestionText();

    String getCategoryName();

    String getTypeName();

    LocalDateTime getTimeCreate();
}
