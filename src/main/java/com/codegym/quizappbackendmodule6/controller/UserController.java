package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.TeacherResponseDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserResponseDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserWithApprovalsProjection;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/approval-pending")
    public ResponseEntity<List<UserWithApprovalsProjection>> getUsersWithApprovals() {
        List<UserWithApprovalsProjection> users = userService.findUsersWithApprovals();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherResponseDTO>> getTeachers() {
        List<TeacherResponseDTO> teacherResponseDTOs = userService.findTeachers();
        if (teacherResponseDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacherResponseDTOs);
    }

    @PutMapping("/approval/{id}")
    public ResponseEntity<User> approveUser(@PathVariable Long id) {
        User user = (User) userService.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + id));
        user.setApproved(true);
        userService.save(user);
        return ResponseEntity.ok(user);
    }
}


