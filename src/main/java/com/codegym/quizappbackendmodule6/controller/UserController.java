package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.configs.PasswordGenerator;
import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.model.dto.auth.ChangePasswordDto;
import com.codegym.quizappbackendmodule6.service.Impl.EmailService;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import com.codegym.quizappbackendmodule6.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final TeacherApprovalService teacherApprovalService;
    private final EmailService emailService;

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileUpdateDto profileUpdateDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User updateUser = userService.updateUserProfile(email, profileUpdateDto);
        if (updateUser != null) {
            return ResponseEntity.ok(updateUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDto changePasswordDTO, Authentication authentication) {
        try {
            if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
                return ResponseEntity.badRequest().body("New passwords and confirm password do not match");
            }

            userService.changePassword(authentication.getName(), changePasswordDTO.getCurrentPassword(), changePasswordDTO.getNewPassword());
            return ResponseEntity.ok("Password changed successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
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

    @GetMapping("/search-student")
    public ResponseEntity<List<UserSearchResponseDTO>> getStudentsByNameAndEmail(@RequestParam String name, @RequestParam String email) {
        List<UserSearchResponseDTO> userSearchResponseDTOs = userService.findUsersByRolesAndNameOrEmail(2L, name, email);
        if (userSearchResponseDTOs.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userSearchResponseDTOs);
    }

    @GetMapping("/admin-info")
    public ResponseEntity<AdminInfoResponseDTO> getAdminInfo() {
        return ResponseEntity.ok(userService.findUsersByRoleId(1L));
    }

    @PutMapping("/update-admin-info")
    public ResponseEntity<Void> updateAdminInfo(@RequestParam Long id, @RequestParam String name, @RequestParam String email, @RequestParam String currentPassword, @RequestParam(required = false) String newPassword, @RequestParam(required = false) MultipartFile avatar) {

        AdminInfoRequestDTO adminInfoRequestDTO = new AdminInfoRequestDTO();
        adminInfoRequestDTO.setId(id);
        adminInfoRequestDTO.setName(name);
        adminInfoRequestDTO.setEmail(email);
        adminInfoRequestDTO.setCurrentPassword(currentPassword);
        adminInfoRequestDTO.setNewPassword(newPassword);
        adminInfoRequestDTO.setAvatar(avatar);

        userService.updateAdminInfo(adminInfoRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/request-teacher-role")
    public ResponseEntity<String> requestTeacherRole(@RequestBody Map<String, String> payload) {
        String userIdStr = payload.get("user_id");
        if (userIdStr == null) {
            return ResponseEntity.badRequest().body("User ID is required");
        }

        Long userId;
        try {
            userId = Long.parseLong(userIdStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid User ID format");
        }

        Optional<User> userOptional = userService.findById(userId);
        if (userOptional.isPresent()) {
            TeacherApproval teacherApproval = new TeacherApproval();
            teacherApproval.setUser(userOptional.get());
            teacherApproval.setStatus(TeacherApproval.Status.PENDING);
            teacherApprovalService.save(teacherApproval);
            return ResponseEntity.ok("Teacher role request submitted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/get-pending-status")
    public ResponseEntity<?> getPendingStatus(@RequestBody Map<String, String> payload) {
        String userIdStr = payload.get("user_id");
        if (userIdStr == null) {
            return ResponseEntity.badRequest().body("User ID is required");
        }

        Long userId;
        try {
            userId = Long.parseLong(userIdStr);
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("Invalid User ID format");
        }

        TeacherApproval teacherApproval = teacherApprovalService.findByUserId(userId);
        if (teacherApproval != null && teacherApproval.getStatus() == TeacherApproval.Status.PENDING) {
            return ResponseEntity.ok(teacherApproval);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
