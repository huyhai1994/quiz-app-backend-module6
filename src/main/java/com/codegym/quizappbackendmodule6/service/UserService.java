package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.*;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    User getUserByEmail(String email);
    void changePassword(String email, String currentPassword, String newPassword);
    void setNewPassword(String email, String newPassword);
    User updateUserProfile(String email, UserProfileUpdateDto profileUpdateDto);

    User updateUser(User user);
    User getUserById(Long id);

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

}
