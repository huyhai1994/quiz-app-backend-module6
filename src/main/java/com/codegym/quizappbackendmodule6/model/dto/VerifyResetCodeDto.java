package com.codegym.quizappbackendmodule6.model.dto;

import lombok.Data;

@Data
public class VerifyResetCodeDto {
    private String email;
    private String resetCode;
}
