package com.codegym.quizappbackendmodule6.model.dto.auth;

import lombok.Data;

@Data
public class VerifyResetCodeDto {
    private String email;
    private String resetCode;
}
