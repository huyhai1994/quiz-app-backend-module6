package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();
    List<Role> findRoleByName(String roleName);
    Role saveRole(Role role);
}
