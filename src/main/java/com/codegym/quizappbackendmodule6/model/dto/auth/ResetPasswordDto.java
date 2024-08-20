package com.codegym.quizappbackendmodule6.model.dto.auth;

import lombok.Data;

@Data
public class ResetPasswordDto {
    private String email;
    private String newPassword;
    private String confirmPassword;
}
