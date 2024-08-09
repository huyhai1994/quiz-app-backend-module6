package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.DTO.LoginRequest;

public interface AuthService {
    User register(User user);
    String login(LoginRequest loginRequest);
}
