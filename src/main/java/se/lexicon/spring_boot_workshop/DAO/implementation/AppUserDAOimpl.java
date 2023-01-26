package se.lexicon.spring_boot_workshop.DAO.implementation;

import org.springframework.stereotype.Repository;
import se.lexicon.spring_boot_workshop.DAO.AppUserDao;
import se.lexicon.spring_boot_workshop.exception.DataNotFoundException;
import se.lexicon.spring_boot_workshop.models.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Repository
public class AppUserDAOimpl implements AppUserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public AppUser create(AppUser appUser) {
        entityManager.persist(appUser);
        return appUser;
    }

    @Override
    @Transactional
    public Optional<AppUser> findById(Integer id) {
        // return Optional.ofNullable(entityManager.find(AppUser.class, id));
        // the method above means that jpa will generate: select * from appUser a where a.id =:id;

        /* alternative 1:
        entityManager.createQuery("select a from AppUser a where a.id = ?1")
                .setParameter(1, id)
                .getResultStream()
                .findFirst();*/

        // Alternative 2:
        return entityManager.createNamedQuery("appUser.findById", AppUser.class)
                .setParameter(1,id)
                .getResultStream()
                .findFirst();
    }

    @Override
    @Transactional
    public Collection<AppUser> findAll() {
        return entityManager.createQuery("select a from AppUser a", AppUser.class)
                .getResultList();
    }

    @Override
    @Transactional
    public AppUser update(AppUser appUser) {
        return entityManager.merge(appUser);
    }

    @Override
    @Transactional
    public void remove(Integer id) throws DataNotFoundException {
        AppUser result = entityManager.find(AppUser.class, id);
        if (result != null){
        entityManager.remove(result);
    } else throw new DataNotFoundException("AppUser was not found");

    }
}
