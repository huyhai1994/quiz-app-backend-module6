package com.codegym.quizappbackendmodule6.model.DTO;

public class TeacherApprovalDTO {
    private Long idTeacherApprovals;
    private String userName;
    private String userEmail;
    private String teacherApprovalsStatus;
    private String approvalAt;

    public TeacherApprovalDTO(Long idTeacherApprovals, String userName, String userEmail, String teacherApprovalsStatus, String approvalAt) {
        this.idTeacherApprovals = idTeacherApprovals;
        this.userName = userName;
        this.userEmail = userEmail;
        this.teacherApprovalsStatus = teacherApprovalsStatus;
        this.approvalAt = approvalAt;
    }

    public Long getIdTeacherApprovals() {
        return idTeacherApprovals;
    }

    public void setIdTeacherApprovals(Long idTeacherApprovals) {
        this.idTeacherApprovals = idTeacherApprovals;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getTeacherApprovalsStatus() {
        return teacherApprovalsStatus;
    }

    public void setTeacherApprovalsStatus(String teacherApprovalsStatus) {
        this.teacherApprovalsStatus = teacherApprovalsStatus;
    }

    public String getApprovalAt() {
        return approvalAt;
    }

    public void setApprovalAt(String approvalAt) {
        this.approvalAt = approvalAt;
    }
}
