package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;


public interface PageableCrudService<T, ID> {
    Page<T> findAll(int pageNumber, String sortField, String sortDirection);
    T findById(ID id);
    T save(ID id, T object);
}
