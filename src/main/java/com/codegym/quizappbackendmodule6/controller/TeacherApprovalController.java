package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/teacher-approvals")
@RequiredArgsConstructor
public class TeacherApprovalController {
    private final TeacherApprovalService teacherApprovalService;

    @GetMapping("/list")
    public ResponseEntity<List<TeacherApproval>> getAllApprovals() {
        return ResponseEntity.ok(teacherApprovalService.findAllApprovals());
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<TeacherApproval> approveRequest(@PathVariable Long id) {
        TeacherApproval approval = teacherApprovalService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu với ID: " + id));
        approval.setStatus("Approved");
        return ResponseEntity.ok(teacherApprovalService.saveApproval(approval));
    }

    @PutMapping("/reject/{id}")
    public ResponseEntity<TeacherApproval> rejectRequest(@PathVariable Long id) {
        TeacherApproval approval = teacherApprovalService.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy yêu cầu với ID: " + id));
        approval.setStatus("Rejected");
        return ResponseEntity.ok(teacherApprovalService.saveApproval(approval));
    }
}

