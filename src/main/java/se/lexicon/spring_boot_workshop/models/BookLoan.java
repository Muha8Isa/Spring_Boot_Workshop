package se.lexicon.spring_boot_workshop.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@NamedQuery(name = "bookLoan.findById", query = "select b from BookLoan b where b.id = ?1")
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int loanId;

    LocalDate localDate;
    LocalDate dueDate;
    boolean returned;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "AppUserId")
    AppUser borrower;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "bookId")
    Book book;




    public BookLoan() {
    }

    public BookLoan(LocalDate localDate, LocalDate dueDate, boolean returned, AppUser borrower, Book book) {
        this.localDate = localDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    public int getLoanId() {
        return loanId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public AppUser getBorrower() {
        return borrower;
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookLoan bookLoan = (BookLoan) o;
        return loanId == bookLoan.loanId && returned == bookLoan.returned && Objects.equals(localDate, bookLoan.localDate) && Objects.equals(dueDate, bookLoan.dueDate) && Objects.equals(borrower, bookLoan.borrower) && Objects.equals(book, bookLoan.book);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, localDate, dueDate, returned, borrower, book);
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanId=" + loanId +
                ", localDate=" + localDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", borrower=" + borrower +
                ", book=" + book +
                '}';
    }
}
