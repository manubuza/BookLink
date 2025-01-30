package com.booklink.HotelBooking.service;

import com.booklink.HotelBooking.mapper.BookMapper;
import com.booklink.HotelBooking.model.dto.BookRequest;
import com.booklink.HotelBooking.model.dto.BookResponse;
import com.booklink.HotelBooking.model.entity.Book;
import com.booklink.HotelBooking.model.entity.User;
import com.booklink.HotelBooking.repository.BookRepository;
import com.booklink.HotelBooking.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addBook_success() {
        // Arrange
        BookRequest bookRequest = new BookRequest();
        bookRequest.setOwnerId(1L);

        User user = new User();
        user.setId(1L);

        Book book = new Book();
        Book savedBook = new Book();
        BookResponse expectedResponse = new BookResponse();

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(bookMapper.toEntity(bookRequest)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(savedBook);
        when(bookMapper.toResponse(savedBook)).thenReturn(expectedResponse);

        // Act
        BookResponse actualResponse = bookService.addBook(bookRequest);

        // Assert
        assertNotNull(actualResponse);
        verify(userRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(book);
        verify(bookMapper, times(1)).toResponse(savedBook);
    }

    @Test
    void addBook_ownerNotFound() {
        // Arrange
        BookRequest bookRequest = new BookRequest();
        bookRequest.setOwnerId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookService.addBook(bookRequest));
        verify(userRepository, times(1)).findById(1L);
        verifyNoInteractions(bookRepository, bookMapper);
    }

    @Test
    void getBooksByFilters_allFilters() {
        // Arrange
        String genre = "Fiction";
        String condition = "New";
        String availability = "Borrow";
        Book book = new Book();
        BookResponse bookResponse = new BookResponse();

        when(bookRepository.findByGenreAndConditionAndAvailability(genre, condition, availability))
                .thenReturn(Arrays.asList(book));
        when(bookMapper.toResponse(book)).thenReturn(bookResponse);

        // Act
        List<BookResponse> actualResponses = bookService.getBooksByFilters(genre, condition, availability);

        // Assert
        assertEquals(1, actualResponses.size());
        verify(bookRepository, times(1))
                .findByGenreAndConditionAndAvailability(genre, condition, availability);
        verify(bookMapper, times(1)).toResponse(book);
    }

    @Test
    void getBookById_success() {
        // Arrange
        Long bookId = 1L;
        Book book = new Book();
        BookResponse bookResponse = new BookResponse();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        when(bookMapper.toResponse(book)).thenReturn(bookResponse);

        // Act
        BookResponse actualResponse = bookService.getBookById(bookId);

        // Assert
        assertNotNull(actualResponse);
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookMapper, times(1)).toResponse(book);
    }

    @Test
    void getBookById_notFound() {
        // Arrange
        Long bookId = 1L;

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookService.getBookById(bookId));
        verify(bookRepository, times(1)).findById(bookId);
        verifyNoInteractions(bookMapper);
    }

    @Test
    void updateBook_success() {
        // Arrange
        Long bookId = 1L;
        BookRequest bookRequest = new BookRequest();
        Book book = new Book();
        Book updatedBook = new Book();
        BookResponse expectedResponse = new BookResponse();

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));
        doNothing().when(bookMapper).updateEntity(bookRequest, book);
        when(bookRepository.save(book)).thenReturn(updatedBook);
        when(bookMapper.toResponse(updatedBook)).thenReturn(expectedResponse);

        // Act
        BookResponse actualResponse = bookService.updateBook(bookId, bookRequest);

        // Assert
        assertNotNull(actualResponse);
        verify(bookRepository, times(1)).findById(bookId);
        verify(bookMapper, times(1)).updateEntity(bookRequest, book);
        verify(bookRepository, times(1)).save(book);
        verify(bookMapper, times(1)).toResponse(updatedBook);
    }

    @Test
    void updateBook_notFound() {
        // Arrange
        Long bookId = 1L;
        BookRequest bookRequest = new BookRequest();

        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> bookService.updateBook(bookId, bookRequest));
        verify(bookRepository, times(1)).findById(bookId);
        verifyNoInteractions(bookMapper);
    }

    @Test
    void deleteBook_success() {
        // Arrange
        Long bookId = 1L;

        doNothing().when(bookRepository).deleteById(bookId);

        // Act
        bookService.deleteBook(bookId);

        // Assert
        verify(bookRepository, times(1)).deleteById(bookId);
    }
}
