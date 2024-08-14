package com.codegym.quizappbackendmodule6.controller;

import com.codegym.quizappbackendmodule6.model.Category;
import com.codegym.quizappbackendmodule6.model.dto.AddCategoryIntoQuizDTO;
import com.codegym.quizappbackendmodule6.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<List<Category>> getAllCategory() {
        List<Category> categoryList = categoryService.findAllCategories();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Category>> findByName(@RequestParam String name) {
        List<Category> categories = categoryService.findCategoryByName(name);
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bạn đã nhập sai trường");
        }
        Category createdCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/ {id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @Valid @RequestBody Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("Bạn đã nhập sai trường");
        }
        Category updatedCategory = categoryService.updateCategory(category,id);
        return ResponseEntity.ok(updatedCategory);
    }
    @GetMapping("/list/{id}")
    public ResponseEntity<Optional<Category>> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/details/{userId}")
    public ResponseEntity<List<AddCategoryIntoQuizDTO>> getCategoryDetailsByUserId(@PathVariable Long userId) {
        List<AddCategoryIntoQuizDTO> categoryDetails = categoryService.findCategoryDetailsByUserId(userId);
        return ResponseEntity.ok(categoryDetails);
    }
}
