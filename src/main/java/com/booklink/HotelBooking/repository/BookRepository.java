package com.booklink.HotelBooking.repository;

import com.booklink.HotelBooking.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByGenre(String genre);

    List<Book> findByCondition(String condition);

    List<Book> findByAvailability(String availability);

    List<Book> findByGenreAndConditionAndAvailability(String genre, String condition, String availability);
}