package com.codegym.quizappbackendmodule6.model.DTO;

import java.time.LocalDateTime;

public class QuestionDTO {
    private Long questionId;
    private String questionText;
    private String categoryName;
    private String typeName;
    private LocalDateTime timeCreate;

    public QuestionDTO(Long questionId, String questionText, String categoryName, String typeName, LocalDateTime timeCreate) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.categoryName = categoryName;
        this.typeName = typeName;
        this.timeCreate = timeCreate;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public LocalDateTime getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(LocalDateTime timeCreate) {
        this.timeCreate = timeCreate;
    }
}
