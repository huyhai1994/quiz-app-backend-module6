package com.codegym.quizappbackendmodule6.model.DTO;

import java.time.LocalDateTime;

public interface QuizTeacherDTO {
    Long getQuizzesId();
    String getQuizzesTitle();
    String getQuizzesDescription();
    String getUsersName();
    LocalDateTime getQuizzesTimeCreate();
}
