package com.codegym.quizappbackendmodule6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "users")
@JsonIgnoreProperties({"quizSessions", "createdBy"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Tên người dùng không được để trống")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Column(unique = true, nullable = false)
    private String email;

    @NotEmpty(message = "Mật khẩu không được để trống")
    @Column(nullable = false)
    private String password;

    private String avatar;

    @Column(name = "registered_at")
    private LocalDateTime registeredAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Role role;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "reset_code")
    private String resetCode;

    @Column(name = "reset_code_expiry")
    private LocalDateTime resetCodeExpiry;

    @OneToMany(mappedBy = "user")
    private Set<QuizSession> quizSessions;

    @PrePersist
    public void prePersist() {
        this.registeredAt = LocalDateTime.now();
        if (this.role == null) {
            Role studentRole = new Role();
            studentRole.setId(2L);
            studentRole.setName("ROLE_STUDENT");
            this.role = studentRole;
        }
    }

    public void setRoleId(int roleId) {
        Role role = new Role();
        role.setId((long) roleId);
        this.role = role;
    }

    public boolean isResetCodeValid() {
        return
                this.resetCode != null &&
                        this.resetCodeExpiry != null &&
                        LocalDateTime.now().isBefore(this.resetCodeExpiry);
    }

    public void clearResetCode() {
        this.resetCode = null;
        this.resetCodeExpiry = null;
    }

    public String getRoleName() {
        return this.role != null ? this.role.getName() : null;
    }

    public void setApprovalStatus(String approved) {
    }
}
