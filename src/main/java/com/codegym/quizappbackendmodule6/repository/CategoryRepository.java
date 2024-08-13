package com.codegym.quizappbackendmodule6.repository;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.dto.AddCategoryIntoQuizDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByName(String name);

    @Query(value = "SELECT c.id AS categoryId, c.name AS categoryName, c.description AS categoryDescription " +
            "FROM categories c " +
            "JOIN users u ON c.created_by = u.id " +
            "WHERE u.id = :userId", nativeQuery = true)
    List<AddCategoryIntoQuizDTO> findCategoryDetailsByUserId(@Param("userId") Long userId);


}