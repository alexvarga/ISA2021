package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;

import java.util.Set;

public interface AdventureReservationService extends PageableCrudReservationService<AdventureReservation, Long> {

    Set<AdventureReservation> getAllByAdventure_Id(Long adventure_id);


}
