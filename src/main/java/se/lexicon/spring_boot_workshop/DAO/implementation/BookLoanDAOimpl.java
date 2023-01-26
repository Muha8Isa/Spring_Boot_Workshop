package se.lexicon.spring_boot_workshop.DAO.implementation;

import org.springframework.stereotype.Repository;
import se.lexicon.spring_boot_workshop.DAO.BookLoanDAO;
import se.lexicon.spring_boot_workshop.exception.DataNotFoundException;
import se.lexicon.spring_boot_workshop.models.BookLoan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public class BookLoanDAOimpl implements BookLoanDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public BookLoan create(BookLoan bookLoan) {
        entityManager.persist(bookLoan);
        return bookLoan;
    }

    @Override
    @Transactional
    public Optional<BookLoan> findById(Integer id) {
        return entityManager.createNamedQuery("appUser.findById", BookLoan.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public Collection<BookLoan> findAll() {
        return entityManager.createQuery("select b from BookLoan b", BookLoan.class)
                .getResultList();
    }

    @Override
    @Transactional
    public BookLoan update(BookLoan bookLoan) {
        return entityManager.merge(bookLoan);
    }

    @Override
    @Transactional
    public void remove(Integer id) throws DataNotFoundException {
        BookLoan result = entityManager.find(BookLoan.class, id);
        if (result != null){
            entityManager.remove(result);
        } else throw new DataNotFoundException("BookLoan was not found");
    }
}
