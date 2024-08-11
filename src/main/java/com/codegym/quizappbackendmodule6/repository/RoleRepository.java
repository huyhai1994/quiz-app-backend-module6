package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value = "SELECT * FROM roles WHERE name LIKE %:roleName%", nativeQuery = true)
    List<Role> findAllByName(String roleName);

    boolean existsByName(String name);
}
