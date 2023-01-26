package se.lexicon.spring_boot_workshop.models;

import org.aspectj.bridge.ILifecycleAware;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "book.findById", query = "select b from Book b where b.id = ?1")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int bookId;

    @Column(nullable = false, length = 13,unique = true)
    String isbn;

    @Column
    String title;
    int maxLoanDays;

    @ManyToMany(mappedBy = "writtenBooks")
    Set<Author> authors;

    public Book() { // Class 'Book' should have [public, protected] no-arg constructor, that is why this constructor is used.
    }

    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public int getBookId() {
        return bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMaxLoanDays() {
        return maxLoanDays;
    }

    public void setMaxLoanDays(int maxLoanDays) {
        this.maxLoanDays = maxLoanDays;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
    if (author == null) throw new IllegalArgumentException("author data was null");
    if (authors == null) authors = new HashSet<>();
    authors.add(author);
    author.addBook(this); // For the other side.
    }
    public void removeAuthor(Author author){
        if (author == null) throw new IllegalArgumentException("author data was null");
        if (authors != null){
            author.removeBook(this);
            authors.remove(author);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && maxLoanDays == book.maxLoanDays && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, isbn, title, maxLoanDays);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", maxLoanDays=" + maxLoanDays +
                '}';
    }
}
