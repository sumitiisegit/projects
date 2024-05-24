package com.demo.library.model;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "Borrower")
public class Borrower {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "borrower_id")
    String borrowerId;

    @Column(name = "name")
    String name;

    @Column(name = "email")
    String emailAddress;

    public Borrower(String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public String getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(String borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Borrower borrower = (Borrower) o;
        return Objects.equals(name, borrower.name) &&
                Objects.equals(emailAddress, borrower.emailAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowerId, name, emailAddress);
    }
}
