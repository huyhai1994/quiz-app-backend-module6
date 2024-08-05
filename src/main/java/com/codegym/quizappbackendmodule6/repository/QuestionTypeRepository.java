package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionTypeRepository extends JpaRepository<QuestionType,Long> {
}
