package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

public interface PageableCrudReservationService<T, ID> extends PageableCrudService<T, ID> {

    Page<T> getAllByUser(User user, int pageNumber, String sortField, String sortDirection );

    Page<T> getAllByUserAndDateEndBefore(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);

    Page<T> getAllByUserAndDateEndAfter(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);

    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end, Long id);

    Page<T> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);


    Page<T> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);

    Page<T> getAllByReservationType(ReservationType type, int pageNumber, String sortField, String sortDirection);
}
