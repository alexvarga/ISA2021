package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;
import java.util.Set;

public interface AdventureReservationService extends CrudService<AdventureReservation, Long> {

    Set<AdventureReservation> getAllByUser(User user);

    Set<AdventureReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time);

    Set<AdventureReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time);

}
