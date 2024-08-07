package com.codegym.quizappbackendmodule6.model.DTO;

import java.time.LocalDateTime;

public interface QuizDTO {
    Long getQuizzesId();
    String getQuizzesTitle();
    String getQuizzesDescription();
    String getUsersName();
    String getUserEmail();
    LocalDateTime getQuizzesTimeCreate();
}
