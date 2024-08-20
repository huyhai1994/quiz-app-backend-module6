package com.codegym.quizappbackendmodule6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "quiz_sesstions")
@JsonIgnoreProperties({"user", "quizRoom"})
public class QuizSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_room_id")
    private QuizRoom quizRoom;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "joined_at")
    private LocalDateTime joinedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Enumerated(EnumType.STRING)
    private SessionStatus status;

    @OneToOne(mappedBy = "quizSession", cascade = CascadeType.ALL)
    private Result result;

    public enum SessionStatus {
        JOINED, IN_PROGRESS, COMPLETED
    }

    @PrePersist
    public void prePersist() {
        this.joinedAt = LocalDateTime.now();
        this.status = SessionStatus.JOINED;
    }
}
