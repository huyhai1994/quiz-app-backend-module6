package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/teacher-approvals")
@RequiredArgsConstructor
public class TeacherApprovalController {
    private final TeacherApprovalService teacherApprovalService;

}

