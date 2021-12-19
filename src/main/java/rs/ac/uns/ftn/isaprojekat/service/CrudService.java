package rs.ac.uns.ftn.isaprojekat.service;

import java.util.Set;

public interface CrudService<T, ID> {
    Set<T> findAll();
    T findById(ID id);
    T save(ID id, T object);

}
