package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.auth.LoginRequest;
import com.codegym.quizappbackendmodule6.model.dto.auth.LoginResponse;
import com.codegym.quizappbackendmodule6.model.dto.auth.UserRegistrationDto;

public interface AuthService {
    User register(UserRegistrationDto registrationDTO);
    LoginResponse login(LoginRequest loginRequest);
    void logout(String token);
}
