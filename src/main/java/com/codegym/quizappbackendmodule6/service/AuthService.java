package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.LoginRequest;
import com.codegym.quizappbackendmodule6.model.dto.UserRegistrationDTO;

public interface AuthService {
    User register(UserRegistrationDTO registrationDTO);
    String login(LoginRequest loginRequest);
}
