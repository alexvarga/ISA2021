package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.repository.BoatRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class BoatJpaService implements BoatService {

    private final BoatRepository boatRepository;

    public BoatJpaService(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @Override
    public Set<Boat> findAll() {
        Set<Boat> boats = new HashSet<>();
        boatRepository.findAll().forEach(boats::add);

        return boats;
    }

    @Override
    public Boat findById(Long aLong) {
        return boatRepository.findById(aLong).orElse(null);
    }

    @Override
    public Boat save(Long aLong, Boat object) {
        return boatRepository.save(object);
    }
}
