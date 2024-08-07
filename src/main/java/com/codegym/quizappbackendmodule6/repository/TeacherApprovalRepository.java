package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.DTO.TeacherApprovalDTO;
import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherApprovalRepository extends JpaRepository<TeacherApproval, Long> {
    @Query(value = "SELECT ta.id AS idteacherapprovals, u.name AS username, u.email AS useremail, ta.status AS teacherapprovalsstatus , ta.approved_at " + "FROM teacher_approvals ta " + "JOIN users u ON ta.user_id = u.id " + "WHERE ta.status = :status", nativeQuery = true)
    List<TeacherApprovalDTO> findAllPending(String status);

    @Query(value = "SELECT ta.id AS idteacherapprovals, u.name AS username, u.email AS useremail, ta.status AS teacherapprovalsstatus , ta.approved_at AS approvedat" + "from teacher_approvals ta " + "JOIN users u ON ta.user_id = u.id " + "WHERE ta.status = :status", nativeQuery = true)
    List<TeacherApprovalDTO> findAllApproval(String status);

    @Query(value = "SELECT * FROM teacher_approvals t WHERE t.user_id = :userid", nativeQuery =
            true)
    TeacherApproval findByUserId(Long userid);
}
