package com.codegym.quizappbackendmodule6.service.Impl;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.dto.AddCategoryIntoQuizDTO;
import com.codegym.quizappbackendmodule6.repository.CategoryRepository;
import com.codegym.quizappbackendmodule6.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        List<Category> existingCategory = categoryRepository.findByName(category.getName());
        if (existingCategory.isEmpty()) {
            return categoryRepository.save(category);
        }
        throw new RuntimeException("Đã tồn tại tên " + category.getName());
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Category không tồn tại với ID: " + id);
        }
    }

    @Override
    public Category updateCategory(Category category ,Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category không tồn tại với ID: " + id);
        }
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public List<AddCategoryIntoQuizDTO> findCategoryDetailsByUserId(Long userId) {
        return categoryRepository.findCategoryDetailsByUserId(userId);
    }
}
