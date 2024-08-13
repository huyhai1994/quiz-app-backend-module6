package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Result;
import com.codegym.quizappbackendmodule6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result,Long> {
    List<Result> findByUser(User user);

}
