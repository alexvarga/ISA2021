package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.repository.BoatRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

@Profile("default")
@Service
public class BoatJpaService implements BoatService {

    private final BoatRepository boatRepository;

    public BoatJpaService(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    @Override
    public Page<Boat> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber-1, 2); //zero based index
//        Set<Boat> boats = new HashSet<>();
//        boatRepository.findAll().forEach(boats::add);
        return boatRepository.findAll(pageable);
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
