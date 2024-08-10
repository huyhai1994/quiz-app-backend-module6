package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    List<UserWithApprovalsProjection> findUsersWithApprovals();

    List<TeacherResponseDTO> findTeachers();

    List<StudentResponseDTO> findStudents();

    User approveUser(Long id);


    void save(User user);

    void deleteUser(Long userId);

    Optional<User> findById(Long id);

    void requestTeacherRole(Long userId);

    void updateUserPassword(Long userId, String newPassword);

    List<UserSearchResponseDTO> findUsersByRolesAndNameOrEmail(Long roleId, String name, String email);

    AdminInfoResponseDTO findUsersByRoleId(Long roleId);

    void updateAdminInfo(AdminInfoRequestDTO adminInfoRequestDTO);

    boolean checkCurrentPassword(Long userId, String oldPassword);
}
