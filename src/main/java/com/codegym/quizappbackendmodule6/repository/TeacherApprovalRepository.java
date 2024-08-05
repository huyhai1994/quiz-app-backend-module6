package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherApprovalRepository extends JpaRepository<TeacherApproval, Long> {
    // Custom method to retrieve User and TeacherApproval data
    List<TeacherApproval> findAllByUser(User user);
}
