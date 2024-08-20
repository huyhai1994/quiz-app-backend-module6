package com.codegym.quizappbackendmodule6.model.dto.auth;

import lombok.Data;

@Data
public class PasswordResetRequestDto {
    private String email;
}
