package se.lexicon.spring_boot_workshop.DAO.implementation;

import org.springframework.stereotype.Repository;
import se.lexicon.spring_boot_workshop.DAO.BaseDAO;
import se.lexicon.spring_boot_workshop.DAO.DetailsDAO;
import se.lexicon.spring_boot_workshop.exception.DataNotFoundException;
import se.lexicon.spring_boot_workshop.models.AppUser;
import se.lexicon.spring_boot_workshop.models.Details;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Repository
public class DetailsDAOimpl implements DetailsDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public Details create(Details details) {
        entityManager.persist(details);
        return details;
    }

    @Override
    @Transactional
    public Optional<Details> findById(Integer id) {
        return entityManager.createNamedQuery("details.findById", Details.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public Collection<Details> findAll() {
        return entityManager.createQuery("select d from Details d", Details.class)
                .getResultList();
    }

    @Override
    @Transactional
    public Details update(Details details) {
        return entityManager.merge(details);
    }

    @Override
    @Transactional
    public void remove(Integer id) throws DataNotFoundException {
        Details result = entityManager.find(Details.class, id);
        if (result != null){
            entityManager.remove(result);
        } else throw new DataNotFoundException("AppUser was not found");

    }
}
