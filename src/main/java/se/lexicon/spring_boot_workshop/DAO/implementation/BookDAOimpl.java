package se.lexicon.spring_boot_workshop.DAO.implementation;

import org.springframework.stereotype.Repository;
import se.lexicon.spring_boot_workshop.DAO.BookDAO;
import se.lexicon.spring_boot_workshop.exception.DataNotFoundException;
import se.lexicon.spring_boot_workshop.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public class BookDAOimpl implements BookDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Book create(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public Optional<Book> findById(Integer id) {
        return entityManager.createNamedQuery("book.findById", Book.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public Collection<Book> findAll() {
        return entityManager.createQuery("select b from Book b", Book.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    @Transactional
    public void remove(Integer id) throws DataNotFoundException {
        Book result = entityManager.find(Book.class, id);
        if (result != null){
            entityManager.remove(result);
        } else throw new DataNotFoundException("BookLoan was not found");
    }

    }
