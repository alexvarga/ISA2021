package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;
import rs.ac.uns.ftn.isaprojekat.repository.AdventureRepository;
import rs.ac.uns.ftn.isaprojekat.service.AdventureService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class AdventureJpaService implements AdventureService {
    private final AdventureRepository adventureRepository;

    public AdventureJpaService(AdventureRepository adventureRepository) {
        this.adventureRepository = adventureRepository;
    }

    @Override
    public Set<Adventure> findAll() {
        Set<Adventure> adventures = new HashSet<>();
        adventureRepository.findAll().forEach(adventures::add);

        return adventures;
    }

    @Override
    public Adventure findById(Long aLong) {
        return adventureRepository.findById(aLong).orElse(null);
    }

    @Override
    public Adventure save(Long aLong, Adventure object) {
        return adventureRepository.save(object);
    }
}
