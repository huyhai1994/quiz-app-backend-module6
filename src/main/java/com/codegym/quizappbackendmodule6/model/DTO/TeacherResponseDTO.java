package com.codegym.quizappbackendmodule6.model.DTO;

import java.time.LocalDateTime;

public interface TeacherResponseDTO {
    Long getId();

    String getEmail();

    String getName();

    LocalDateTime getLastLogin();

    LocalDateTime getRegisteredAt();

    String getRoleName();
}