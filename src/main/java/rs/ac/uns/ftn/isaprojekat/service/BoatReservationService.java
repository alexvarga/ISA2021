package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;
import java.util.Set;

public interface BoatReservationService extends CrudService<BoatReservation, Long> {

    Set<BoatReservation> getAllByUser(User user);

    Set<BoatReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time);

    Set<BoatReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time);
}