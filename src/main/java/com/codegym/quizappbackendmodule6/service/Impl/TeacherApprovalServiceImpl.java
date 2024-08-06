package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.repository.TeacherApprovalRepository;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherApprovalServiceImpl implements TeacherApprovalService {
    private final TeacherApprovalRepository teacherApprovalRepository;

    @Override
    public TeacherApproval save(TeacherApproval teacherApproval) {
        return teacherApprovalRepository.save(teacherApproval);
    }
}
