package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
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

    @Override
    public Set<VacationHouseReservation> getAllByUser(User user) {
        return vacationHouseReservationRepository.getAllByUser(user);
    }

    @Override
    public Set<VacationHouseReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time) {
        return vacationHouseReservationRepository.getAllByUserAndDateEndBefore(user, time);
    }

    @Override
    public Set<VacationHouseReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time) {
        return vacationHouseReservationRepository.getAllByUserAndDateFromAfter(user, time);
    }
}
