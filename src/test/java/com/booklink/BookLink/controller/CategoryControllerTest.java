package com.booklink.BookLink.controller;

import com.booklink.BookLink.controller.CategoryController;
import com.booklink.BookLink.model.dto.CategoryDto;
import com.booklink.BookLink.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnBadRequestWhenNameIsMissing() throws Exception {
        // Arrange: CategoryDto with missing name
        CategoryDto CategoryDto = new CategoryDto();
        CategoryDto.setName(null); // Missing name
        CategoryDto.setDescription("Description for category");

        // Act & Assert
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CategoryDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Category name is required"));
    }

    @Test
    void shouldAddCategorySuccessfully() throws Exception {
        // Arrange: Valid CategoryDto
        CategoryDto categoryDtoRequest = new CategoryDto();
        categoryDtoRequest.setName("Fiction");
        categoryDtoRequest.setDescription("A category for fictional books");

        // Expected response
        CategoryDto categoryDtoResponse = new CategoryDto();
        categoryDtoResponse.setId(1L);
        categoryDtoResponse.setName("Fiction");
        categoryDtoResponse.setDescription("A category for fictional books");

        // Mock service behavior
        given(categoryService.addCategory(any(CategoryDto.class))).willReturn(categoryDtoResponse);

        // Act & Assert
        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDtoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Fiction"))
                .andExpect(jsonPath("$.description").value("A category for fictional books"));
    }
}
