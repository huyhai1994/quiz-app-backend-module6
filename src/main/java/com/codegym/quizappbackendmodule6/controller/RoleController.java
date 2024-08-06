package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Role;
import com.codegym.quizappbackendmodule6.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/list")
    public ResponseEntity<List<Role>> findAll() {
        List<Role> roleList = roleService.findAllRoles();
        return ResponseEntity.ok(roleList);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Role>> findByName(@RequestParam String name) {
        List<Role> roles = roleService.findRoleByName(name);
        return ResponseEntity.ok(roles);
    }

    @PostMapping("/create")
    public ResponseEntity<Role> create(@RequestBody Role role) {
        Role savedRole = roleService.saveRole(role);
        return ResponseEntity.ok(savedRole);
    }
}
