package com.demo.library.controller;

import com.demo.library.model.Borrower;
import com.demo.library.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/v1/borrower/")
public class BorrowerController {

    @Autowired
    BorrowerService borrowerService;

    @PostMapping("/addNewBorrower")
    public ResponseEntity<String> addNewBorrower(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "email") String email) {
        if (null != borrowerService.addNewBorrower(name, email))
            return ResponseEntity.ok().body(HttpStatus.OK.toString());
        else
            return ResponseEntity.badRequest().body("Not able to add new borrower.");
    }

    @GetMapping("/listAll")
    public ResponseEntity<List<Borrower>> listAllBorrower() {
        return ResponseEntity.ok().body(borrowerService.listAllBorrowers());
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<String> borrowBook(
            @RequestParam(name = "bookId") String bookId,
            @RequestParam(name = "borrowerId") String borrowerId) {
        if (borrowerService.borrowBook(bookId, borrowerId))
            return ResponseEntity.ok().body(HttpStatus.OK.toString());
        else
            return ResponseEntity.badRequest().body("Not able to borrow book.");
    }

    @PostMapping("/returnBook")
    public ResponseEntity<String> returnBook(
            @RequestParam(name = "bookId") String bookId,
            @RequestParam(name = "borrowerId") String borrowerId) {
        if (borrowerService.returnBook(bookId, borrowerId))
            return ResponseEntity.ok().body(HttpStatus.OK.toString());
        else
            return ResponseEntity.badRequest().body("Not able to borrow book.");
    }

}
