package com.codegym.quizappbackendmodule6.model.dto;

import java.time.LocalDateTime;

public interface StudentResponseDTO {

    Long getId();

    String getEmail();

    String getName();

    LocalDateTime getLastLogin();

    LocalDateTime getRegisteredAt();

}
