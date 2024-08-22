package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.auth.LoginRequest;
import com.codegym.quizappbackendmodule6.model.dto.auth.LoginResponse;
import com.codegym.quizappbackendmodule6.model.dto.auth.UserRegistrationDto;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public User register(UserRegistrationDto registrationDTO) {
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
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            logger.info("Attempting login for user: {}", loginRequest.getEmail());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
            logger.info("Authentication successful for user: {}", loginRequest.getEmail());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);

            User user = userRepository.findByEmail(loginRequest.getEmail());
            if (user == null) {
                throw new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail());
            }

            user.setLastLogin(LocalDateTime.now());
            userRepository.save(user);

            logger.info("Login successful for user: {}", user.getEmail());
            return new LoginResponse(jwt, user.getId(), user.getName(), user.getEmail(), user.getRole().getName(), user.getLastLogin());
        } catch (Exception e) {
            logger.error("Login failed for user: {}. Error: {}", loginRequest.getEmail(), e.getMessage());
            throw e;
        }
    }

    @Override
    public void logout(String token) {
        jwtTokenProvider.invalidateToken(token);
        SecurityContextHolder.clearContext();
        logger.info("User logged out successfully");
    }
}
