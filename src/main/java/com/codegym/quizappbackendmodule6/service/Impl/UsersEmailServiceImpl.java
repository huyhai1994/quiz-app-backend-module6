package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.service.UsersEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UsersEmailServiceImpl implements UsersEmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public void sendRegistrationConfirmationEmail(User user) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(user.getEmail());
        message.setSubject("Registration Confirmation");
        message.setText("Dear " + user.getName() + ",\n\nThank you for registering with our application. " +
                "Please click the following link to complete your registration:\n\n" +
                "http://localhost:3000/\n\nBest regards,\nYour Application Team");
        mailSender.send(message);
    }

    @Override
    public void sendPasswordResetEmail(String to, String resetCode) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(to);
        message.setSubject("Password Reset Code");
        message.setText(
                "Your password reset code is: " + resetCode +
                "\n\nPlease use this code to reset your password.\n\nThis code will expire in 3 minutes."
        );
        mailSender.send(message);
    }
}
