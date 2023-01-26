package se.lexicon.spring_boot_workshop.models;

import javax.persistence.*;
import java.time.LocalDate;

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


}
