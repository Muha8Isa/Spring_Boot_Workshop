package se.lexicon.spring_boot_workshop.DAO.implementation;

import org.springframework.stereotype.Repository;
import se.lexicon.spring_boot_workshop.DAO.AuthorDAO;
import se.lexicon.spring_boot_workshop.exception.DataNotFoundException;
import se.lexicon.spring_boot_workshop.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Repository
public class AuthorDAOimpl implements AuthorDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Author create(Author author) {
        entityManager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public Optional<Author> findById(Integer id) {
        return entityManager.createNamedQuery("author.findById", Author.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public Collection<Author> findAll() {
        return entityManager.createQuery("select a from Author a", Author.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Author update(Author author) {
        return entityManager.merge(author);
    }

    @Override
    @Transactional
    public void remove(Integer id) throws DataNotFoundException {
        Author result = entityManager.find(Author.class, id);
        if (result != null){
            entityManager.remove(result);
        } else throw new DataNotFoundException("BookLoan was not found");
    }
}
