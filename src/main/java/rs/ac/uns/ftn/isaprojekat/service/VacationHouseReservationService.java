package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;

import java.util.Set;

public interface VacationHouseReservationService extends CrudService<VacationHouseReservation, Long> {

    Set<VacationHouseReservation> getAllByUser(User user);
}
