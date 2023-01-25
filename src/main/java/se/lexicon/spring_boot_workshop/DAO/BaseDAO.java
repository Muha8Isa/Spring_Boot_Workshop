package se.lexicon.spring_boot_workshop.DAO;

import se.lexicon.spring_boot_workshop.exception.DataNotFoundException;

import java.util.Collection;
import java.util.Optional;

public interface BaseDAO <T, ID> {

    T create(T t);
    Optional<T> findById(ID id);

    Collection<T> findAll();

    T update(T t);

    void remove(ID id) throws DataNotFoundException;
}
