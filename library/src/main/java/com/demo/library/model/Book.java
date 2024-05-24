package com.demo.library.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    String bookId;

    @Column(name = "isbn_num")
    Long isbnNum;

    @Column(name = "title")
    String title;

    @Column(name = "author")
    String author;

    @Column(name = "isBorrowed")
    boolean isBorrowed;

    @ManyToOne
    @JoinColumn(columnDefinition = "borrowedBy")
    Borrower borrower;


    public Book(Long isbnNum, String title, String author) {
        this.isbnNum = isbnNum;
        this.title = title;
        this.author = author;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Long getIsbnNum() {
        return isbnNum;
    }

    public void setIsbnNum(Long isbnNum) {
        this.isbnNum = isbnNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getIsBorrowed() {
        return isBorrowed;
    }

    public void setIsBorrowed(boolean isBorrowed) {
        this.isBorrowed = isBorrowed;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        /*
         *** 2 books with the same title and same author but different ISBN numbers are considered as different books
         *** - 2 books with the same ISBN numbers must have the same title and same author
         */

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        Boolean sameIsbn = Objects.equals(isbnNum, book.isbnNum) &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);

        Boolean diffIsbn =
                Objects.equals(title, book.title) &&
                        Objects.equals(author, book.author) &&
                        !Objects.equals(isbnNum, book.isbnNum);

        return true ? true == sameIsbn : false ? diffIsbn : false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbnNum, title, author, isBorrowed, borrower);
    }
}
