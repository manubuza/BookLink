package com.booklink.BookLink.controller;

import com.booklink.BookLink.model.dto.BookRequest;
import com.booklink.BookLink.model.dto.BookResponse;
import com.booklink.BookLink.service.BookService;
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

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addBook() throws Exception {
        BookRequest bookRequest = createBookRequest();
        BookResponse expectedResponse = createBookResponse();

        given(bookService.addBook(any(BookRequest.class))).willReturn(expectedResponse);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(expectedResponse.getId()))
                .andExpect(jsonPath("$.title").value(expectedResponse.getTitle()))
                .andExpect(jsonPath("$.author").value(expectedResponse.getAuthor()))
                .andExpect(jsonPath("$.isbn").value(expectedResponse.getIsbn()))
                .andExpect(jsonPath("$.genre").value(expectedResponse.getGenre()))
                .andExpect(jsonPath("$.condition").value(expectedResponse.getCondition()))
                .andExpect(jsonPath("$.availability").value(expectedResponse.getAvailability()))
                .andExpect(jsonPath("$.ownerName").value(expectedResponse.getOwnerName()));
    }

    @Test
    void shouldReturnBadRequestWhenTitleIsMissing() throws Exception {
        BookRequest bookRequest = createBookRequest();
        bookRequest.setTitle(null); // Missing title

        // Act & Assert
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.title").value("Title is required"));
    }

    @Test
    void shouldReturnBadRequestWhenAuthorIsMissing() throws Exception {
        // Arrange: BookRequest with missing author
        BookRequest bookRequest = createBookRequest();
        bookRequest.setAuthor(null); // Missing author

        // Act & Assert
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.author").value("Author is required"));
    }

    @Test
    void shouldReturnBadRequestWhenOwnerIdIsMissing() throws Exception {
        // Arrange: BookRequest with missing ownerId
        BookRequest bookRequest = createBookRequest();
        bookRequest.setOwnerId(null); // Missing ownerId

        // Act & Assert
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.ownerId").value("Owner ID is required"));
    }

    @Test
    void shouldReturnBadRequestWhenAvailabilityIsInvalid() throws Exception {
        // Arrange: BookRequest with invalid availability
        BookRequest bookRequest = createBookRequest();
        bookRequest.setAvailability(""); // Invalid availability

        // Act & Assert
        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.availability").value("Availability is required"));
    }

    // Helper method to create a mock BookRequest
    private BookRequest createBookRequest() {
        BookRequest bookRequest = new BookRequest();
        bookRequest.setTitle("The Great Gatsby");
        bookRequest.setAuthor("F. Scott Fitzgerald");
        bookRequest.setIsbn("9780743273565");
        bookRequest.setGenre("Classic");
        bookRequest.setCondition("Good");
        bookRequest.setAvailability("borrow");
        bookRequest.setOwnerId(1L);
        return bookRequest;
    }

    // Helper method to create a mock BookResponse
    private BookResponse createBookResponse() {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(1L);
        bookResponse.setTitle("The Great Gatsby");
        bookResponse.setAuthor("F. Scott Fitzgerald");
        bookResponse.setIsbn("9780743273565");
        bookResponse.setGenre("Classic");
        bookResponse.setCondition("Good");
        bookResponse.setAvailability("borrow");
        bookResponse.setOwnerName("John Doe");
        return bookResponse;
    }
}
