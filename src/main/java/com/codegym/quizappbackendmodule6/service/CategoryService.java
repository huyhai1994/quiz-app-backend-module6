package com.codegym.quizappbackendmodule6.service;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.dto.AddCategoryIntoQuizDTO;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findCategoryByName(String name);
    List<Category> findAllCategories();

    Optional<Category> findById(Long id);
    Category saveCategory(Category category);
    void deleteCategory(Long id);
    Category updateCategory(Category category, Long id);

    List<AddCategoryIntoQuizDTO> findCategoryDetailsByUserId(Long userId);
}
