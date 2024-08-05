package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
