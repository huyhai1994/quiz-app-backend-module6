package com.codegym.quizappbackendmodule6.model.DTO;

import java.time.LocalDateTime;

public class QuizDTO {
    private Long quizzesId;
    private String quizzesTitle;
    private String quizzesDescription;
    private String usersName;
    private String userEmail;
    private LocalDateTime quizzesTimeCreate;

    public QuizDTO(Long quizzesId, String quizzesTitle, String quizzesDescription, String usersName, String userEmail, LocalDateTime quizzesTimeCreate) {
        this.quizzesId = quizzesId;
        this.quizzesTitle = quizzesTitle;
        this.quizzesDescription = quizzesDescription;
        this.usersName = usersName;
        this.userEmail = userEmail;
        this.quizzesTimeCreate = quizzesTimeCreate;
    }

    public Long getQuizzesId() {
        return quizzesId;
    }

    public void setQuizzesId(Long quizzesId) {
        this.quizzesId = quizzesId;
    }

    public String getQuizzesTitle() {
        return quizzesTitle;
    }

    public void setQuizzesTitle(String quizzesTitle) {
        this.quizzesTitle = quizzesTitle;
    }

    public String getQuizzesDescription() {
        return quizzesDescription;
    }

    public void setQuizzesDescription(String quizzesDescription) {
        this.quizzesDescription = quizzesDescription;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDateTime getQuizzesTimeCreate() {
        return quizzesTimeCreate;
    }

    public void setQuizzesTimeCreate(LocalDateTime quizzesTimeCreate) {
        this.quizzesTimeCreate = quizzesTimeCreate;
    }
}
