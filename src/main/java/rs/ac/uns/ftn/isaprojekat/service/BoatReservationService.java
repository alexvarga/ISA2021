package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

public interface BoatReservationService extends PageableCrudReservationService<BoatReservation, Long> {

    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end);



}
