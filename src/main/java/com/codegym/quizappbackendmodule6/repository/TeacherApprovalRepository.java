package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.dto.TeacherApprovalDTO;
import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherApprovalRepository extends JpaRepository<TeacherApproval,Long> {
    @Query(value = "SELECT ta.id AS idTeacherApprovals, u.name AS userName, u.email AS userEmail, ta.status AS teacherApprovalsStatus, ta.approved_at AS approvedAt " +
            "FROM teacher_approvals ta " +
            "JOIN users u ON ta.user_id = u.id " +
            "WHERE ta.status = :status",
            nativeQuery = true)
    List<TeacherApprovalDTO> findAllPending(String status);

    @Query(value = "SELECT ta.id AS idTeacherApprovals, u.name AS userName, u.email AS userEmail, ta.status AS teacherApprovalsStatus, ta.approved_at AS approvedAt " +
            "FROM teacher_approvals ta " +
            "JOIN users u ON ta.user_id = u.id " +
            "WHERE ta.status = :status",
            nativeQuery = true)
    List<TeacherApprovalDTO> findAllApproval(String status);
    @Query(value = "SELECT * FROM TeacherApproval t WHERE t.user_id = :userId" , nativeQuery = true)
    TeacherApproval findByUserId(Long userId);
}
