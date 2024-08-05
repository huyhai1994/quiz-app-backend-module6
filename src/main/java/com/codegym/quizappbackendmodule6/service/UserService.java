package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void deleteUser(Long userId);
    Optional<User> findById(Long id);
}
