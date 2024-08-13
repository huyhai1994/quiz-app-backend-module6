package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.dto.TeacherApprovalDTO;
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
    public ResponseEntity<List<TeacherApproval>> getAllTeacherApprovals() {
        List<TeacherApproval> teacherApprovalList = teacherApprovalService.getAll();
        return ResponseEntity.ok(teacherApprovalList);
    }

    @GetMapping("/pending")
    public ResponseEntity<List<TeacherApprovalDTO>> getAllPendingApprovals() {
        List<TeacherApprovalDTO> pendingApprovals = teacherApprovalService.findAllPending();
        return ResponseEntity.ok(pendingApprovals);
    }

    @GetMapping("/approved")
    public ResponseEntity<List<TeacherApprovalDTO>> getAllApprovedApprovals() {
        List<TeacherApprovalDTO> approvedApprovals = teacherApprovalService.findAllApproval();
        return ResponseEntity.ok(approvedApprovals);
    }

    @GetMapping("/search/approved")
    public ResponseEntity<List<TeacherApprovalDTO>> findAllApprovalByNameAndEmail(
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String userEmail) {
        List<TeacherApprovalDTO> approvedApprovals = teacherApprovalService.findAllApprovalByNameAndEmail("APPROVED", userName, userEmail);
        return ResponseEntity.ok(approvedApprovals);
    }

}

