package com.codegym.quizappbackendmodule6.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @ColumnDefault("'pending'")
    private Status status;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @PrePersist
    public void approvedAt() {
        if (status == Status.PENDING) {
            this.approvedAt = null;
        } else {
            this.approvedAt = LocalDateTime.now();
        }
    }

    public void setApprovalStatus(String approved) {
        if (approved.equalsIgnoreCase("APPROVED")) {
            this.status = Status.APPROVED;
        } else {
            throw new IllegalArgumentException("Invalid approval status: " + approved);
        }
    }

    public enum Status {
        PENDING, APPROVED
    }
}
