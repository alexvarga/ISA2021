package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;

import java.util.ArrayList;


public interface PageableCrudService<T, ID> {
    Page<T> findAll(int pageNumber, String sortField, String sortDirection);
    ArrayList<T> findAll();
    T findById(ID id);
    T save(ID id, T object);

    void deleteById(ID id);
}
