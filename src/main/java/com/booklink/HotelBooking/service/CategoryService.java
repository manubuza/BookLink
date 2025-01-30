package com.booklink.HotelBooking.service;

import com.booklink.HotelBooking.mapper.CategoryMapper;
import com.booklink.HotelBooking.model.dto.CategoryDto;
import com.booklink.HotelBooking.model.entity.Category;
import com.booklink.HotelBooking.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    public CategoryDto addCategory(CategoryDto CategoryDto) {
        Category category = categoryMapper.toEntity(CategoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return categoryMapper.toDto(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}