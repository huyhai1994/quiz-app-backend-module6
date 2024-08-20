package com.codegym.quizappbackendmodule6.model.dto;

import java.time.LocalDateTime;

public interface UserWithApprovalsProjection {
    Long getId();

    String getName();

    String getEmail();

    LocalDateTime getLastLogin();

    LocalDateTime getRegisteredAt();

}
