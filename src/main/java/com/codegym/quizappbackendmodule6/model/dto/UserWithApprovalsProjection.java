package com.codegym.quizappbackendmodule6.model.dto;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;

import java.time.LocalDateTime;

public interface UserWithApprovalsProjection {
    Long getId();

    String getAvatar();

    String getName();

    String getEmail();

    Boolean getIsDeleted();

    LocalDateTime getLastLogin();

    LocalDateTime getRegisteredAt();

    String getPassword();

    Long getRoleId();

    TeacherApproval.Status getApprovalStatus();

    LocalDateTime getApprovedAt();
}
