package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;
import rs.ac.uns.ftn.isaprojekat.repository.AdventureRepository;
import rs.ac.uns.ftn.isaprojekat.service.AdventureService;

@Profile("default")
@Service
public class AdventureJpaService implements AdventureService {
    private final AdventureRepository adventureRepository;

    public AdventureJpaService(AdventureRepository adventureRepository) {
        this.adventureRepository = adventureRepository;
    }

    @Override
    public Page<Adventure> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber-1, 2, sort); //zero based index

        return adventureRepository.findAll(pageable);
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
