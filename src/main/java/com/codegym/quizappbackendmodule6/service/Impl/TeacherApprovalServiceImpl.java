package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.dto.TeacherApprovalDTO;
import com.codegym.quizappbackendmodule6.repository.TeacherApprovalRepository;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherApprovalServiceImpl implements TeacherApprovalService {
    private final TeacherApprovalRepository teacherApprovalRepository;

    @Override
    public TeacherApproval save(TeacherApproval teacherApproval) {
        return teacherApprovalRepository.save(teacherApproval);
    }

    @Override
    public List<TeacherApproval> getAll() {
        return teacherApprovalRepository.findAll();
    }


    @Override
    public TeacherApproval findByUserId(Long id) {
        return teacherApprovalRepository.findByUserId(id);
    }

    @Override
    public List<TeacherApprovalDTO> findAllPending() {
        return teacherApprovalRepository.findAllPending("PENDING");
    }

    @Override
    public List<TeacherApprovalDTO> findAllApproval() {
        return teacherApprovalRepository.findAllApproval("APPROVED");
    }

    @Override
    public List<TeacherApprovalDTO> findAllApprovalByNameAndEmail(String status, String userName, String userEmail) {
        return teacherApprovalRepository.findAllApprovalByNameAndEmail(status, userName, userEmail);
    }
}
