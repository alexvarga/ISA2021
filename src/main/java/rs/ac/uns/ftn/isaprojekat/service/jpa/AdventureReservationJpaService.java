package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class AdventureReservationJpaService implements AdventureReservationService {

    private final AdventureReservationRepository adventureReservationRepository;

    public AdventureReservationJpaService(AdventureReservationRepository adventureReservationRepository) {
        this.adventureReservationRepository = adventureReservationRepository;
    }

    @Override
    public Set<AdventureReservation> findAll() {
        Set<AdventureReservation> reservations = new HashSet<>();
        adventureReservationRepository.findAll().forEach(reservations::add);

        return reservations;
    }

    @Override
    public AdventureReservation findById(Long aLong) {
        return adventureReservationRepository.findById(aLong).orElse(null);
    }

    @Override
    public AdventureReservation save(Long aLong, AdventureReservation object) {
        return adventureReservationRepository.save(object);
    }
}
