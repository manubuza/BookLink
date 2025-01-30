package com.booklink.HotelBooking.mapper;

import com.booklink.HotelBooking.model.dto.CategoryDto;
import com.booklink.HotelBooking.model.entity.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);

    List<Category> toEntityList(List<CategoryDto> categoryDtos);

}
