package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.LoginRequest;
import com.codegym.quizappbackendmodule6.model.dto.LoginResponse;
import com.codegym.quizappbackendmodule6.model.dto.UserRegistrationDto;

public interface AuthService {
    User register(UserRegistrationDto registrationDTO);
    LoginResponse login(LoginRequest loginRequest);
    void logout(String token);
}
