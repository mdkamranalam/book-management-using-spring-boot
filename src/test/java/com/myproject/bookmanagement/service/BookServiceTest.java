package com.myproject.bookmanagement.service;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.myproject.bookmanagement.model.Book;
import com.myproject.bookmanagement.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @InjectMocks
    private BookService bookService;

    @Test
    public void testSearchByTitle() {
        Book book = new Book();
        book.setTitle("Test Book");
        when(bookRepository.findByTitleContainingIgnoreCase("test")).thenReturn(List.of(book));

        List<Book> books = bookService.searchByTitle("test");
        assertFalse(books.isEmpty());
    }
}
