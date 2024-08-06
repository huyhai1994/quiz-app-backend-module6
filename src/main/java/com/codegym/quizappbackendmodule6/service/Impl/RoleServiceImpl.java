package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Role;
import com.codegym.quizappbackendmodule6.repository.RoleRepository;
import com.codegym.quizappbackendmodule6.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRoleByName(String roleName) {
        return roleRepository.findAllByName(roleName);
    }

    @Override
    @Transactional
    public Role saveRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new IllegalArgumentException("Role đã tồn tại");
        }
        return roleRepository.save(role);
    }
}
