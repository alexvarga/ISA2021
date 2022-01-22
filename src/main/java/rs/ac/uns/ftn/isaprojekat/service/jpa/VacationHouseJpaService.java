package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Profile("default")
@Service
public class VacationHouseJpaService implements VacationHouseService {

    private final VacationHouseRepository vacationHouseRepository;
    private int itemsPerPage = 2;

    public VacationHouseJpaService(VacationHouseRepository vacationHouseRepository) {
        this.vacationHouseRepository = vacationHouseRepository;
    }

    @Override
    public Page<VacationHouse> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort); //zero based index

        return vacationHouseRepository.findAll(pageable);
    }

    @Override
    public VacationHouse findById(Long aLong) {
        return vacationHouseRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouse save(Long aLong, VacationHouse object) {
        return vacationHouseRepository.save(object);
    }

    @Override
    public Page<VacationHouse> findVacationHousesNotReserved(int pageNumber, String sortField, String sortDirection,
                                                             LocalDateTime dateFrom, LocalDateTime dateEnd,
                                                             Float maxPrice, Float minRating,
                                                             Integer noOfPersons, String tag1,
                                                             String tag2, String tag3) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }


        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);


        return vacationHouseRepository.findVacationHousesNotReserved(dateFrom, dateEnd, maxPrice,
                minRating, noOfPersons, tag1, tag2, tag3, pageable);
    }

    @Override
    public void deleteById(Long aLong) {
        vacationHouseRepository.deleteById(aLong);
    }

    @Override
    public ArrayList<VacationHouse> findAll() {
        return null;
    }
}
