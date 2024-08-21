package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.QuizCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuizCategoryRepository extends JpaRepository<QuizCategory, Long> {

}
