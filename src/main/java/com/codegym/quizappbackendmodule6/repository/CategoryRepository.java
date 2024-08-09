package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName(String name);
}
