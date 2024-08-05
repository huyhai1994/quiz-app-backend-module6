package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.UserAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAnswerRepository extends JpaRepository<UserAnswer,Long> {
}
