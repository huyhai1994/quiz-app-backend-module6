package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.dto.auth.PasswordResetRequestDto;
import com.codegym.quizappbackendmodule6.model.dto.auth.ResetPasswordDto;
import com.codegym.quizappbackendmodule6.model.dto.auth.VerifyResetCodeDto;
import com.codegym.quizappbackendmodule6.service.Impl.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/password-reset")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/request")
    public ResponseEntity<?> requestPasswordReset(@RequestBody PasswordResetRequestDto requestDto) {
        try {
            passwordResetService.sendResetCode(requestDto.getEmail());
            return ResponseEntity.ok("reset code sent to your email");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyResetCode(@RequestBody VerifyResetCodeDto verifyResetCodeDto) {
        boolean isValid = passwordResetService.verifyResetCode(verifyResetCodeDto.getEmail(), verifyResetCodeDto.getResetCode());
        if (isValid) {
            return ResponseEntity.ok("reset code verified successfully");
        } else {
            return ResponseEntity.badRequest().body("invalid reset code");
        }
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        try {
            passwordResetService.resetPassword(
                    resetPasswordDto.getEmail(),
                    resetPasswordDto.getNewPassword(),
                    resetPasswordDto.getConfirmPassword()
            );
            return ResponseEntity.ok("password reset successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
