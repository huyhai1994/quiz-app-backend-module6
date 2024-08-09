package com.codegym.quizappbackendmodule6.model.DTO;

import java.time.LocalDateTime;

public interface TeacherApprovalDTO {
    Long getIdTeacherApprovals();
    String getUserName();
    String getUserEmail();
    String getTeacherApprovalsStatus();
    LocalDateTime getApprovedAt();
}
