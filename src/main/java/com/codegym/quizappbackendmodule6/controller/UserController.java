package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.UserResponseDTO;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
// Implement user-related operations here, such as creating, updating, deleting, and retrieving users.
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        List<UserResponseDTO> userResponseDTOs = users.stream().map(user -> new UserResponseDTO(user.getName(), user.getEmail(), user.getAvatar(), user.getRegisteredAt(), user.getLastLogin())).collect(Collectors.toList());
        return ResponseEntity.ok(userResponseDTOs);
    }
}


