package com.booklink.HotelBooking.controller;

import com.booklink.HotelBooking.model.dto.BookRequest;
import com.booklink.HotelBooking.model.dto.BookResponse;
import com.booklink.HotelBooking.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Endpoints for managing books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    @Operation(summary = "Add a new book", description = "Allows a user to add a new book")
    public ResponseEntity<BookResponse> addBook(@RequestBody @Valid BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.addBook(bookRequest));
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve a list of all available books with optional filters")
    public ResponseEntity<List<BookResponse>> getAllBooks(
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String condition,
            @RequestParam(required = false) String availability) {
        return ResponseEntity.ok(bookService.getBooksByFilters(genre, condition, availability));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book details", description = "Retrieve detailed information about a specific book")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update details of an existing book")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Valid BookRequest bookRequest) {
        return ResponseEntity.ok(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book", description = "Delete an existing book")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}