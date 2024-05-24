package com.demo.library.services;

import com.demo.library.model.Book;
import com.demo.library.model.Borrower;
import com.demo.library.repository.BookRepository;
import com.demo.library.repository.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowerService {

    @Autowired
    BorrowerRepository borrowerRepository;

    @Autowired
    BookRepository bookRepository;

    public void addNewBorrower(Borrower borrower) {
        borrowerRepository.save(borrower);
    }

    public Borrower addNewBorrower(String name, String emailAddress) {
        Borrower borrower = new Borrower(name, emailAddress);
        return borrowerRepository.save(borrower);
    }

    public List<Borrower> listAllBorrowers() {
        return borrowerRepository.findAll();
    }

    public boolean borrowBook(String bookId, String borrowerId) {
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        if (null != borrower.get()) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isPresent() && (!(book.get().getIsBorrowed()))) {
                Book book1 = book.get();
                book1.setIsBorrowed(Boolean.TRUE);
                book1.setBorrower(borrower.get());
                bookRepository.save(book1);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }


    public boolean returnBook(String bookId, String borrowerId) {
        Optional<Borrower> borrower = borrowerRepository.findById(borrowerId);
        if (null != borrower.get()) {
            Optional<Book> book = bookRepository.findById(bookId);
            if (book.isPresent() && !book.get().getIsBorrowed()) {
                Book book1 = book.get();
                book1.setIsBorrowed(Boolean.FALSE);
                book1.setBorrower(null);
                bookRepository.save(book1);
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
