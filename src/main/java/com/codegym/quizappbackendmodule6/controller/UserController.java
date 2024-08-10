package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.dto.AdminInfoRequestDTO;
import com.codegym.quizappbackendmodule6.model.dto.AdminInfoResponseDTO;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin("*")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PutMapping("/update-admin-info")
    public ResponseEntity<Void> updateAdminInfo(
            @RequestParam Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String currentPassword,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) MultipartFile avatar) {

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

    @GetMapping("/admin-info")
    public ResponseEntity<AdminInfoResponseDTO> getAdminInfo() {
        return ResponseEntity.ok(userService.findUsersByRoleId(1L));
    }
}