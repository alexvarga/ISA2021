package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

public interface PageableCrudReservationService<T, ID> extends PageableCrudService<T, ID> {

    Page<T> getAllByUser(User user, int pageNumber, String sortField, String sortDirection );

    Page<T> getAllByUserAndDateEndBefore(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);

    Page<T> getAllByUserAndDateFromAfter(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);
}
