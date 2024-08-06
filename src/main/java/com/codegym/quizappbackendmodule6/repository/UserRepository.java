package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.TeacherResponseDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserWithApprovalsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "CALL  getuserswithapprovals()", nativeQuery = true)
    List<UserWithApprovalsProjection> getUsersWithApprovals();

    @Query(value = "CALL get_teachers()", nativeQuery = true)
    List<TeacherResponseDTO> getTeachers();

    @Query(value = "SELECT u FROM User u JOIN u.role r WHERE r.id = :roleId AND (u.name LIKE " +
            "%:name% OR u.email LIKE %:email%)")
    List<User> findUsersByRolesAndNameOrEmail(@Param("roleId") Long roleId,
                                              @Param("name") String name, @Param("email") String email);
}
