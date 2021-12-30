package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.BoatReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;



@Profile("default")
@Service
public class BoatReservationJpaService implements BoatReservationService {

    private final BoatReservationRepository boatReservationRepository;

    public BoatReservationJpaService(BoatReservationRepository boatReservationRepository) {
        this.boatReservationRepository = boatReservationRepository;
    }

    @Override
    public Set<BoatReservation> findAll() {
        Set<BoatReservation> reservations = new HashSet<>();
        boatReservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }

    @Override
    public BoatReservation findById(Long aLong) {
        return boatReservationRepository.findById(aLong).orElse(null);
    }

    @Override
    public BoatReservation save(Long aLong, BoatReservation object) {
        return boatReservationRepository.save(object);
    }

    @Override
    public Set<BoatReservation> getAllByUser(User user) {
        return boatReservationRepository.getAllByUser(user);
    }

    @Override
    public Set<BoatReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time) {
        return boatReservationRepository.getAllByUserAndDateEndBefore(user, time);
    }

    @Override
    public Set<BoatReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time) {
        return boatReservationRepository.getAllByUserAndDateEndBefore(user, time);
    }
}
