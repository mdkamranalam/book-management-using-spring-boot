package com.myproject.bookmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.myproject.bookmanagement.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn")
    List<Book> findByIsbn(String isbn);

    @Query("SELECT b FROM Book b JOIN b.author a")
    List<Book> findAllWithAuthor();
}
