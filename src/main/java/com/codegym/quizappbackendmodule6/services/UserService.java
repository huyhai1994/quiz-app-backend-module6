package com.codegym.quizappbackendmodule6.services;

import com.codegym.quizappbackendmodule6.model.User;
import org.springframework.stereotype.Service;
import com.codegym.quizappbackendmodule6.repositories.UserRepository;

import java.util.List;

@Service
public class UserService implements IUserService {
    // Implement methods to handle user-related operations
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}