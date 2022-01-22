package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.BoatOwner;
import rs.ac.uns.ftn.isaprojekat.repository.BoatOwnerRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatOwnerService;

import java.util.ArrayList;

@Profile("default")
@Service
public class BoatOwnerJpaService implements BoatOwnerService {

    private final BoatOwnerRepository boatOwnerRepository;
    public int pageSize = 5;

    public BoatOwnerJpaService(BoatOwnerRepository boatOwnerRepository) {
        this.boatOwnerRepository = boatOwnerRepository;
    }

    @Override
    public Page<BoatOwner> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);

        return boatOwnerRepository.findAll(pageable);
    }

    @Override
    public ArrayList<BoatOwner> findAll() {
        return (ArrayList<BoatOwner>) boatOwnerRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        boatOwnerRepository.deleteById(aLong);
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
