package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    private final TeacherApprovalService teacherApprovalService;

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
}
