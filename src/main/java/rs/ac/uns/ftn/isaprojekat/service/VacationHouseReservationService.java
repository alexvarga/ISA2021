package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;

import java.time.LocalDateTime;
import java.util.Set;

public interface VacationHouseReservationService extends CrudService<VacationHouseReservation, Long> {

    Set<VacationHouseReservation> getAllByUser(User user);

    Set<VacationHouseReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time);

    Set<VacationHouseReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time);

}
