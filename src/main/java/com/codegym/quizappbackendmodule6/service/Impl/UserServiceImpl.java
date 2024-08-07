package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.TeacherApproval;
import com.codegym.quizappbackendmodule6.model.User;
import com.codegym.quizappbackendmodule6.repository.TeacherApprovalRepository;
import com.codegym.quizappbackendmodule6.repository.UserRepository;
import com.codegym.quizappbackendmodule6.service.TeacherApprovalService;
import com.codegym.quizappbackendmodule6.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TeacherApprovalService teacherApprovalService;
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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại với ID: " + userId));
        TeacherApproval teacherApproval = teacherApprovalService.findByUserId(userId);
        if (teacherApproval!= null) {
            throw new RuntimeException("Yêu cầu đã được gửi đi trước đó.");
        }
        TeacherApproval approvalRequest = new TeacherApproval();
        approvalRequest.setUser(user);
        approvalRequest.setStatus("PENDING");
        approvalRequest.setApprovedAt(null);
        teacherApprovalService.save(approvalRequest);
    }
}
