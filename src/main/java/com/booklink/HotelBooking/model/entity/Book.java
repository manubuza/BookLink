package com.booklink.HotelBooking.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false, length = 255)
    private String author;

    @Column(length = 13)
    private String isbn;

    @Column(length = 100)
    private String genre;

    @Column(nullable = false)
    private String condition;

    @Column(nullable = false)
    private String availability;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

}
