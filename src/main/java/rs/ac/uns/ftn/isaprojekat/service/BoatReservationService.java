package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

public interface BoatReservationService extends PageableCrudReservationService<BoatReservation, Long> {

    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end);

    Page<BoatReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);


    Page<BoatReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection);

    Page<BoatReservation> getAllByReservationType(ReservationType type, int pageNumber, String sortField, String sortDirection);

}
