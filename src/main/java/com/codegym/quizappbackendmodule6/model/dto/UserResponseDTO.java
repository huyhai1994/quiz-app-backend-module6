package com.codegym.quizappbackendmodule6.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class UserResponseDTO {
    private String name;
    private String email;
    private String avatar;
    private LocalDateTime registeredAt;
    private LocalDateTime lastLogin;
}