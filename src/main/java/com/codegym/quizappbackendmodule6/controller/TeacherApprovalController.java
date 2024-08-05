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



}

