package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.BoatOwner;
import rs.ac.uns.ftn.isaprojekat.repository.BoatOwnerRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatOwnerService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class BoatOwnerJpaService implements BoatOwnerService {

    private final BoatOwnerRepository boatOwnerRepository;

    public BoatOwnerJpaService(BoatOwnerRepository boatOwnerRepository) {
        this.boatOwnerRepository = boatOwnerRepository;
    }

    @Override
    public Set<BoatOwner> findAll() {
        Set<BoatOwner> boatOwners = new HashSet<>();
        boatOwnerRepository.findAll().forEach(boatOwners::add);
        return boatOwners;
    }

    @Override
    public BoatOwner findById(Long aLong) {
        return boatOwnerRepository.findById(aLong).orElse(null);
    }

    @Override
    public BoatOwner save(Long aLong, BoatOwner object) {
        return boatOwnerRepository.save(object);
    }
}
