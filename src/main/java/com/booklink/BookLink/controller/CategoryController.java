package com.booklink.BookLink.controller;

import com.booklink.BookLink.model.dto.CategoryDto;
import com.booklink.BookLink.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Tag(name = "Categories", description = "Endpoints for managing book categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @PostMapping
    @Operation(summary = "Add a new category", description = "Allows adding a new category")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody @Valid CategoryDto CategoryDto) {
        return ResponseEntity.ok(categoryService.addCategory(CategoryDto));
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve a list of all available categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get category details", description = "Retrieve detailed information about a specific category")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category", description = "Delete an existing category")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
