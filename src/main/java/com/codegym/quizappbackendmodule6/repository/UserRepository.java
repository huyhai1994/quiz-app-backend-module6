package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    Optional<User> getUserByEmail(String email);

    @Query(value = "SELECT u.id AS id, u.name AS name, u.email AS email, u.last_login AS lastLogin, u.registered_at AS registeredAt " +
                   "FROM users u " +
                   "JOIN teacher_approvals ta ON u.id = ta.user_id " +
                   "WHERE ta.status = 'PENDING'",
           nativeQuery = true)
    List<UserWithApprovalsProjection> getUsersWithApprovals();

    @Query(value = "SELECT u.id AS id, u.email AS email, u.name AS name, u.registered_at AS registeredAt, u.last_login AS lastLogin " +
                   "FROM users u " +
                   "JOIN roles r ON u.roles_id = r.id " +
                   "WHERE r.id = 3",
           nativeQuery = true)
    List<TeacherResponseDTO> getTeachers();

    @Query(value = "SELECT u FROM User u JOIN u.role r WHERE r.id = 2")
    List<StudentResponseDTO> getStudents();

    @Query(value = "SELECT u FROM User u JOIN u.role r WHERE r.id = :roleId AND (u.name LIKE " + "%:name% AND u.email LIKE %:email%)")
    List<UserSearchResponseDTO> findUsersByRolesAndNameOrEmail(@Param("roleId") Long roleId, @Param("name") String name, @Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.role.id = :roleId")
    AdminInfoResponseDTO findUserByRoleId(Long roleId);
}
