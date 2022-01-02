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



@Profile("default")
@Service
public class VacationHouseJpaService implements VacationHouseService {

    private final VacationHouseRepository vacationHouseRepository;

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

        Pageable pageable = PageRequest.of(pageNumber-1, 2, sort); //zero based index

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
}
