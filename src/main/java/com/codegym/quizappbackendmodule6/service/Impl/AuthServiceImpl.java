package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.LoginRequest;
import com.codegym.quizappbackendmodule6.repository.UserRepository;
import com.codegym.quizappbackendmodule6.security.JwtTokenProvider;
import com.codegym.quizappbackendmodule6.service.AuthService;
import com.codegym.quizappbackendmodule6.service.EmailServiceRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private EmailServiceRegister emailServiceRegister;

    @Override
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoleId(2);
        user.setName(user.getName());

//        return userRepository.save(user);
        User registeredUser = userRepository.save(user);
        emailServiceRegister.sendRegistrationConfirmationEmail(registeredUser);
        return registeredUser;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtTokenProvider.generateToken(authentication);
    }
}
