package com.booklink.HotelBooking.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title cannot exceed 255 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 255, message = "Author name cannot exceed 255 characters")
    private String author;

    @Size(max = 13, message = "ISBN must be 13 characters or less")
    private String isbn;

    @Size(max = 100, message = "Genre cannot exceed 100 characters")
    private String genre;

    @NotBlank(message = "Condition is required")
    private String condition;

    @NotBlank(message = "Availability is required")
    private String availability;

    @NotNull(message = "Owner ID is required")
    private Long ownerId;

}
