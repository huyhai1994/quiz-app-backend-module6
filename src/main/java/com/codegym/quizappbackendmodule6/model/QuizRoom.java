package com.codegym.quizappbackendmodule6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "quiz_rooms")
@JsonIgnoreProperties({"quiz", "createBy", "quizSessions"})
public class QuizRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomCode;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @JoinColumn(name = "create_by")
    private User createBy;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    @OneToMany(mappedBy = "quizRoom", cascade = CascadeType.ALL)
    private Set<QuizSession> quizSessions = new HashSet<>();

    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Column(name = "current_participants")
    private Integer currentParticipants;

    public enum RoomStatus {
        WAITING, ACTIVE, COMPLETED
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.status = RoomStatus.WAITING;
        this.currentParticipants = 0;
    }
}
