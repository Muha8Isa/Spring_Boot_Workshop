package se.lexicon.spring_boot_workshop.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@NamedQuery(name = "author.findById", query = "select a from Author a where a.id = ?1")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int authorId;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @ManyToMany
    Set<Book> writtenBooks;

    public Author() {
    }

    public Author(String firstName, String lastName, Set<Book> writtenBooks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.writtenBooks = writtenBooks;
    }

    public int getAuthorId() {
        return authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getWrittenBooks() {
        return writtenBooks;
    }

    public void setWrittenBooks(Set<Book> writtenBooks) {
        this.writtenBooks = writtenBooks;
    }

    public void addBook(Book book){
        if (book == null) throw new IllegalArgumentException("book data was null");
        if (writtenBooks == null) writtenBooks = new HashSet<>();
        writtenBooks.add(book);
        book.addAuthor(this); // OR book.getAuthors().add(this);
    }
    public void removeBook(Book book){
        if (book == null) throw new IllegalArgumentException("book data was null");
        book.removeAuthor(this); // Tell the other side to remove it.
        writtenBooks.remove(book);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId && Objects.equals(firstName, author.firstName) && Objects.equals(lastName, author.lastName) && Objects.equals(writtenBooks, author.writtenBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, firstName, lastName, writtenBooks);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", writtenBooks=" + writtenBooks +
                '}';
    }
}
