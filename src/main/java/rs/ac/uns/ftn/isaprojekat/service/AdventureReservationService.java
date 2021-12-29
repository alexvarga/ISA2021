package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface AdventureReservationService extends CrudService<AdventureReservation, Long> {

    Set<AdventureReservation> getAllByUser(User user);
}
