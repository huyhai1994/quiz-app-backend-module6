package com.codegym.quizappbackendmodule6.model.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String token;
    private Long userId;
    private String name;
    private String email;
    private String role;
    private LocalDateTime lastLogin;
}
