package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.QuizSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizSessionRepository extends JpaRepository<QuizSession, Long> {
}
