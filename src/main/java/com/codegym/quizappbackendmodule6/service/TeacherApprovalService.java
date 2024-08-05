package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;

import java.util.List;

public interface TeacherApprovalService {
    TeacherApproval save(TeacherApproval teacherApproval);

    List<TeacherApproval> getAll();

    List<TeacherApproval> getAllByUserName(String userName);
}
