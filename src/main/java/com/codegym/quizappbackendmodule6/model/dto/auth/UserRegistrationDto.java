package com.codegym.quizappbackendmodule6.model.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotEmpty(message = "Tên người dùng không được để trống")
    private String name;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotEmpty(message = "Mật khẩu không được để trống")
    private String password;

    private String avatar;
}
