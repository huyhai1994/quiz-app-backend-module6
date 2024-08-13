package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;

public interface UsersEmailService {
    void sendRegistrationConfirmationEmail(User user);
    void sendPasswordResetEmail(String to, String resetCode);
}
