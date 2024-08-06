package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.TeacherResponseDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserWithApprovalsProjection;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    List<UserWithApprovalsProjection> findUsersWithApprovals();

    List<TeacherResponseDTO> findTeachers();

    User approveUser(Long id);

    Optional<Object> findById(Long id);

    void save(User user);
}