package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.TeacherResponseDTO;
import com.codegym.quizappbackendmodule6.model.dto.UserWithApprovalsProjection;
import com.codegym.quizappbackendmodule6.repository.UserRepository;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TeacherApprovalService teacherApprovalService;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserWithApprovalsProjection> findUsersWithApprovals() {
        return userRepository.getUsersWithApprovals();
    }

    @Override
    public List<TeacherResponseDTO> findTeachers() {
        return userRepository.getTeachers();
    }

    @Override
    public User approveUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setRoleId(3);
        user.setApprovalStatus("APPROVED");
        return userRepository.save(user);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).get();
        if (user == null) {
            throw new IllegalArgumentException("Không tìm thấy User");
        }
        user.setIsDeleted(false);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


    @Override
    public void requestTeacherRole(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Người dùng không tồn tại với ID: " + userId));
        TeacherApproval teacherApproval = teacherApprovalService.findByUserId(userId);
        if (teacherApproval != null) {
            throw new RuntimeException("Yêu cầu đã được gửi đi trước đó.");
        }
        TeacherApproval approvalRequest = new TeacherApproval();
        approvalRequest.setUser(user);
        approvalRequest.setStatus(TeacherApproval.Status.valueOf("PENDING"));
        teacherApprovalService.save(approvalRequest);
    }

    @Override
    public void updateUserPassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(newPassword); // Assuming you have a method to hash the password
        userRepository.save(user);
    }

    @Override
    public List<User> findUsersByRolesAndNameOrEmail(Long roleId, String name, String email) {
        return userRepository.findUsersByRolesAndNameOrEmail(roleId, name, email);
    }
}
