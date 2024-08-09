package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;

public interface EmailServiceRegister {
    void sendRegistrationConfirmationEmail(User user);
}
