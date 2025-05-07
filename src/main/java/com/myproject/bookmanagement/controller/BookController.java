package com.myproject.bookmanagement.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.myproject.bookmanagement.model.Author;
import com.myproject.bookmanagement.model.Book;
import com.myproject.bookmanagement.service.AuthorService;
import com.myproject.bookmanagement.service.BookService;

@Controller
public class BookController {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private AuthorService authorService;
    @Autowired
    private BookService bookService;

    // Home page with book list and search
    @GetMapping("/")
    public String home(Model model, @RequestParam(value = "search", required = false) String search) {
        try {
            logger.info("Accessing home page with search: {}", search);
            List<Book> books;
            if (search != null && !search.isEmpty()) {
                books = bookService.searchByTitle(search);
                if (books.isEmpty()) {
                    books = bookService.searchByIsbn(search);
                }
                if (books.isEmpty()) {
                    List<Author> authors = authorService.searchByName(search);
                    books = authors.stream().flatMap(a -> a.getBooks().stream()).toList();
                }
            } else {
                books = bookService.findAll();
            }
            model.addAttribute("books", books);
            model.addAttribute("authors", authorService.findAll());
            return "index";
        } catch (Exception e) {
            logger.error("Error in home page: {}", e.getMessage(), e);
            return "error";
        }
    }

    // Create Author Form
    @GetMapping("/author/new")
    public String createAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "create_author";
    }

    // Save Author
    @PostMapping("/author")
    public String saveAuthor(@ModelAttribute Author author) {
        try {
            authorService.save(author);
        } catch (Exception e) {
            logger.error("Error saving author: {}", e.getMessage(), e);
            return "error";
        }
        return "redirect:/";
    }

    // Create Book Form
    @GetMapping("/book/new")
    public String createBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAll());
        return "create_book";
    }

    // Save Book
    @PostMapping("/book")
    public String saveBook(@ModelAttribute Book book, @RequestParam("authorId") Long authorId) {
        try {
            authorService.findById(authorId).ifPresent(book::setAuthor);
            bookService.save(book);
        } catch (Exception e) {
            logger.error("Error saving book: {}", e.getMessage(), e);
            return "error";
        }
        return "redirect:/";
    }

    // Update Book Form
    @GetMapping("/book/edit/{id}")
    public String editBookForm(@PathVariable Long id, Model model) {
        try {
            bookService.findById(id).ifPresent(book -> model.addAttribute("book", book));
            model.addAttribute("authors", authorService.findAll());
            return "edit_book";
        } catch (Exception e) {
            logger.error("Error loading edit book form: {}", e.getMessage(), e);
            return "error";
        }
    }

    // Update Book
    @PostMapping("/book/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book book, @RequestParam("authorId") Long authorId) {
        try {
            book.setId(id);
            authorService.findById(authorId).ifPresent(book::setAuthor);
            bookService.save(book);
        } catch (Exception e) {
            logger.error("Error updating book: {}", e.getMessage(), e);
            return "error";
        }
        return "redirect:/";
    }
}
