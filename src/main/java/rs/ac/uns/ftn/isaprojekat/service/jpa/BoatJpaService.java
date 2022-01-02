package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Boat> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }
        //sort = sortDirection.equals("asc") ? sort.ascending() : sort.descending();

        Pageable pageable = PageRequest.of(pageNumber-1, 2, sort); //zero based index


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
