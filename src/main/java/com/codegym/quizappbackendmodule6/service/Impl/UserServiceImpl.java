package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.model.dto.*;
import com.codegym.quizappbackendmodule6.repository.UserRepository;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final TeacherApprovalService teacherApprovalService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUserProfile(String email, UserProfileUpdateDto profileUpdateDto) {
        User currentUser = getUserByEmail(email);
        if (currentUser != null) {
            currentUser.setName(profileUpdateDto.getName());
            currentUser.setAvatar(profileUpdateDto.getAvatar());
            return userRepository.save(currentUser);
        }
        return null;
    }

    @Override
    public void changePassword(String email, String currentPassword, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public void setNewPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
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
    public List<StudentResponseDTO> findStudents() {
        return userRepository.getStudents();
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
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
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
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public List<UserSearchResponseDTO> findUsersByRolesAndNameOrEmail(Long roleId, String name, String email) {
        return userRepository.findUsersByRolesAndNameOrEmail(roleId, name, email);
    }

    @Override
    public AdminInfoResponseDTO findUsersByRoleId(Long roleId) {
        return userRepository.findUserByRoleId(roleId);
    }

    @Override
    public void updateAdminInfo(AdminInfoRequestDTO adminInfoRequestDTO) {
        User admin = userRepository.findById(adminInfoRequestDTO.getId()).orElseThrow(() -> new RuntimeException("Admin not found"));

        if (checkUserCurrentPassword(admin, adminInfoRequestDTO.getCurrentPassword())) {
            throw new RuntimeException("Current password is incorrect");
        }
        updateNewAdminInfo(adminInfoRequestDTO, admin);
        userRepository.save(admin);
    }

    private void updateNewAdminInfo(AdminInfoRequestDTO adminInfoRequestDTO, User admin) {
        admin.setName(adminInfoRequestDTO.getName());
        admin.setEmail(adminInfoRequestDTO.getEmail());
        if (adminInfoRequestDTO.getNewPassword() != null
                &&
                !adminInfoRequestDTO.getNewPassword().isEmpty()) {
            admin.setPassword(passwordEncoder.encode(adminInfoRequestDTO.getNewPassword()));
        }
    }

    private boolean checkUserCurrentPassword(User user, String currentPassword) {
        return !passwordEncoder.matches(currentPassword, user.getPassword());
    }
}