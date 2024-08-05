package com.codegym.quizappbackendmodule6.repositories;

import com.codegym.quizappbackendmodule6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
