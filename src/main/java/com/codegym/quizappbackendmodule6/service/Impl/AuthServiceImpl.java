package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.LoginRequest;
import com.codegym.quizappbackendmodule6.model.dto.UserRegistrationDTO;
import com.codegym.quizappbackendmodule6.repository.UserRepository;
import com.codegym.quizappbackendmodule6.security.JwtTokenProvider;
import com.codegym.quizappbackendmodule6.service.AuthService;
import com.codegym.quizappbackendmodule6.service.UsersEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UsersEmailService usersEmailService;

    @Override
    @Transactional
    public User register(UserRegistrationDTO registrationDTO) {
        logger.info("Registering new user with email: {}", registrationDTO.getEmail());
        if (userRepository.getUserByEmail(registrationDTO.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();
        user.setName(registrationDTO.getName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setAvatar(registrationDTO.getAvatar());
        user.setRoleId(2);

//        return userRepository.save(user);
        User registeredUser = userRepository.save(user);
        usersEmailService.sendRegistrationConfirmationEmail(registeredUser);
        logger.info("User registered successfully: {}", registeredUser.getId());
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
