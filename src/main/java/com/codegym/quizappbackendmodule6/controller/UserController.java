package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.configs.PasswordGenerator;
import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.service.Impl.EmailService;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TeacherApprovalService teacherApprovalService;
    private final EmailService emailService;


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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/request-teacher/{id}")
    public ResponseEntity<Void> requestTeacherRole(@PathVariable Long id) {
        userService.requestTeacherRole(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherResponseDTO>> getTeachers() {
        List<TeacherResponseDTO> teacherResponseDTOs = userService.findTeachers();
        if (teacherResponseDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(teacherResponseDTOs);
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentResponseDTO>> getStudents() {
        return ResponseEntity.ok(userService.findStudents());
    }

    @PutMapping("/approval/{id}")
    public ResponseEntity<User> approveUser(@PathVariable Long id) {
        User user = userService.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại với ID: " + id));
        String randomPassword = PasswordGenerator.generateRandomPassword(10);
        userService.updateUserPassword(id, randomPassword);
        emailService.sendConfirmationEmail(user.getEmail(), "Confirmation Email", "Your account has been approved.", randomPassword);
        user.setRoleId(3);
        TeacherApproval teacherApproval = teacherApprovalService.findByUserId(id); // Assuming a
        teacherApproval.setApprovalStatus("APPROVED");
        teacherApproval.setApprovedAt(LocalDateTime.now());
        userService.save(user);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/search-teacher")
    public ResponseEntity<List<UserSearchResponseDTO>> getTeachersByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        List<UserSearchResponseDTO> userSearchResponseDTOs = userService.findUsersByRolesAndNameOrEmail(3L, name, email);
        if (userSearchResponseDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userSearchResponseDTOs);
    }

}


