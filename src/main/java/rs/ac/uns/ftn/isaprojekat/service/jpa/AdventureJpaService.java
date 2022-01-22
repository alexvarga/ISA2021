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

import java.time.LocalDateTime;
import java.util.ArrayList;

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

    @Override
    public Page<Adventure> findAdventureNotReserved(int pageNumber,
                                                    String sortField,
                                                    String sortDirection,
                                                    LocalDateTime dateFrom,
                                                    LocalDateTime dateEnd,
                                                    Float maxPrice,
                                                    Float minRating,
                                                    Integer noOfPersons,
                                                    String tag1,
                                                    String tag2,
                                                    String tag3) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber-1, 2, sort); //zero based index


        return adventureRepository.findAdventuresNotReserved(dateFrom, dateEnd, maxPrice, minRating, noOfPersons, tag1, tag2, tag3, pageable);
    }

    @Override
    public void deleteById(Long aLong) {
        adventureRepository.deleteById(aLong);
    }

    @Override
    public ArrayList<Adventure> findAll() {
        return (ArrayList<Adventure>) adventureRepository.findAll();
    }


}
