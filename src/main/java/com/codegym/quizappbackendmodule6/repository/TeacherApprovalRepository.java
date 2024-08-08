package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.dto.TeacherApprovalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeacherApprovalRepository extends JpaRepository<TeacherApproval, Long> {
    @Query(value = "SELECT ta.id AS idteacherapprovals, u.name AS username, u.email AS useremail, ta.status AS teacherapprovalsstatus, ta.approved_at AS approvedat " + "FROM teacher_approvals ta " + "JOIN users u ON ta.user_id = u.id " + "WHERE ta.status = :status", nativeQuery = true)
    List<TeacherApprovalDTO> findAllPending(String status);

    @Query(value = "SELECT ta.id AS idteacherapprovals, u.name AS username, u.email AS useremail, ta.status AS teacherapprovalsstatus, ta.approved_at AS approvedat " + "FROM teacher_approvals ta " + "JOIN users u ON ta.user_id = u.id " + "WHERE ta.status = :status", nativeQuery = true)
    List<TeacherApprovalDTO> findAllApproval(String status);

    @Query("SELECT t FROM TeacherApproval t WHERE t.user.id = :userId")
    TeacherApproval findByUserId(@Param("userId") Long userId);

}
