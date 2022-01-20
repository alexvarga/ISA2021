package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface BoatReservationService extends PageableCrudReservationService<BoatReservation, Long> {

    Set<BoatReservation> getAllByBoat_Id(Long boat_id);

    Set<BoatReservation> getAllByUser(User user);



}
