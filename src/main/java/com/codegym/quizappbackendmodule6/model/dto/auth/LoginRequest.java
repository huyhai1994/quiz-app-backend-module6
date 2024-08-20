package com.codegym.quizappbackendmodule6.model.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}