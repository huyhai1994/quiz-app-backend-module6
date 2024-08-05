package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.repository.RoleRepository;
import com.codegym.quizappbackendmodule6.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
}
