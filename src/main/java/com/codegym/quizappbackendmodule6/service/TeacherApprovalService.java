package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.dto.TeacherApprovalDTO;

import java.util.List;

public interface TeacherApprovalService {
    TeacherApproval save(TeacherApproval teacherApproval);

    List<TeacherApproval> getAll();


    TeacherApproval findByUserId(Long id);

    List<TeacherApprovalDTO> findAllPending();

    List<TeacherApprovalDTO> findAllApproval();

    List<TeacherApprovalDTO> findAllApprovalByNameAndEmail(String status, String userName, String userEmail);
}
