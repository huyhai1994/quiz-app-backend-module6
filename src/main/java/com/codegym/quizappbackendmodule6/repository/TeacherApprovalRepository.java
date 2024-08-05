package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherApprovalRepository extends JpaRepository<TeacherApproval,Long> {

}
