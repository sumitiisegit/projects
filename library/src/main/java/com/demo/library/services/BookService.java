package com.demo.library.services;

import com.demo.library.model.Book;
import com.demo.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public void addNewBook(Book book) {
        bookRepository.save(book);
    }

    public Book registerBook(Long isbnNum, String title, String author) {
        Book book = new Book(isbnNum, title, author);
        return bookRepository.save(book);

    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

}
