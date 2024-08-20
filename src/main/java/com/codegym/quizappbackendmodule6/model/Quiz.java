package com.codegym.quizappbackendmodule6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "quizzes")
@JsonIgnoreProperties({"createdBy", "quizRooms", "questions"})
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tiêu đề không được để trống")
    private String title;

    @Lob
    private String description;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @Column(name = "quiz_time")
    private Long quizTime;

    private int quantity;

    @Column(name = "time_create")
    private LocalDateTime timeCreate;

    @Enumerated(EnumType.STRING)
    @Column(name = "difficulty")
    private Difficulty difficulty;

    @PrePersist
    public void setTimeCreate() {
        this.timeCreate = LocalDateTime.now();
    }

    @JsonIgnore
    @Column(name = "passing_score")
    private int passingScore;

    @OneToMany(mappedBy = "quiz")
    private Set<QuizRoom> quizRooms;

    @ManyToOne
    @JoinColumn(name = "quiz_categories_id")
    private QuizCategory quizCategory;

    @ManyToMany
    @JoinTable(
            name = "quiz_questions",
            joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private Set<Question> questions;

    public enum Difficulty {
        HARD, MEDIUM, EASY
    }
}
