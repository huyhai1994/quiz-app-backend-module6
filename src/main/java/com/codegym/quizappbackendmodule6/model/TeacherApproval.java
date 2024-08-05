package com.codegym.quizappbackendmodule6.model;

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
@Table(name = "teacher_approvals")
public class TeacherApproval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String status;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @PrePersist
    public void approvedAt() {
        this.approvedAt = LocalDateTime.now();
    }
}
