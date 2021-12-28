package rs.ac.uns.ftn.isaprojekat.service.jpa;

import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.util.HashSet;
import java.util.Set;

public class VacationHouseReservationJpaService implements VacationHouseReservationService {

    private final VacationHouseReservationRepository vacationHouseReservationRepository;

    public VacationHouseReservationJpaService(VacationHouseReservationRepository vacationHouseReservationRepository) {
        this.vacationHouseReservationRepository = vacationHouseReservationRepository;
    }

    @Override
    public Set<VacationHouseReservation> findAll() {
        Set<VacationHouseReservation> reservations = new HashSet<>();
        vacationHouseReservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    @Override
    public VacationHouseReservation findById(Long aLong) {
        return vacationHouseReservationRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseReservation save(Long aLong, VacationHouseReservation object) {
        return vacationHouseReservationRepository.save(object);
    }
}
