package com.myproject.bookmanagement.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.myproject.bookmanagement.model.Author;
import com.myproject.bookmanagement.model.Book;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        Author author = new Author();
        author.setName("Test Author");
        author = authorRepository.save(author);

        Book book = new Book();
        book.setTitle("Test Book");
        book.setIsbn("1234567890");
        book.setAuthor(author);
        bookRepository.save(book);

        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("test");
        assertFalse(books.isEmpty());
        assertNotNull(books.get(0).getAuthor());
    }
}
