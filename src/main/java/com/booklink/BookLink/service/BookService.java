package com.booklink.BookLink.service;

import com.booklink.BookLink.mapper.BookMapper;
import com.booklink.BookLink.model.dto.BookRequest;
import com.booklink.BookLink.model.dto.BookResponse;
import com.booklink.BookLink.model.entity.Book;
import com.booklink.BookLink.model.entity.User;
import com.booklink.BookLink.repository.BookRepository;
import com.booklink.BookLink.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final BookMapper bookMapper;


    public BookResponse addBook(BookRequest bookRequest) {
        User owner = userRepository.findById(bookRequest.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Owner not found"));

        Book book = bookMapper.toEntity(bookRequest);
        book.setOwner(owner);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    public List<BookResponse> getBooksByFilters(String genre, String condition, String availability) {
        List<Book> books;
        if (genre != null && condition != null && availability != null) {
            books = bookRepository.findByGenreAndConditionAndAvailability(genre, condition, availability);
        } else if (genre != null) {
            books = bookRepository.findByGenre(genre);
        } else if (condition != null) {
            books = bookRepository.findByCondition(condition);
        } else if (availability != null) {
            books = bookRepository.findByAvailability(availability);
        } else {
            books = bookRepository.findAll();
        }
        return books.stream().map(bookMapper::toResponse).collect(Collectors.toList());
    }

    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toResponse(book);
    }

    public BookResponse updateBook(Long id, BookRequest bookRequest) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookMapper.updateEntity(bookRequest, book);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
