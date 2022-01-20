package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;

import java.util.Set;

public interface VacationHouseReservationService extends PageableCrudReservationService<VacationHouseReservation, Long> {

    Set<VacationHouseReservation> getAllByVacationHouse_Id(Long vacationHouse_id);

    Set<VacationHouseReservation> getAllByUser(User user);

}
