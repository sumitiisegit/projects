package com.demo.library.controller;

import com.demo.library.model.Book;
import com.demo.library.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1/books/")
public class BooksController {
    @Autowired
    BookService bookService;

    @PostMapping("/registerBook")
    public ResponseEntity<String> registerBook(
            @RequestParam(name = "isbn") Long isbnNum,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "author") String author) {
        if (null != bookService.registerBook(isbnNum, title, author))
            return ResponseEntity.ok().body(HttpStatus.OK.toString());
        else
            return ResponseEntity.badRequest().body("Not able to save book.");
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Book>> listAllBooks() {
        return ResponseEntity.ok().body(bookService.listAllBooks());
    }

}
