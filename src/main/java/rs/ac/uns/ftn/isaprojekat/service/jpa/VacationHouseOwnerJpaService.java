package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseOwner;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseOwnerRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseOwnerService;

import java.util.ArrayList;

@Profile("default")
@Service
public class VacationHouseOwnerJpaService implements VacationHouseOwnerService {

    private final VacationHouseOwnerRepository vacationHouseOwnerRepository;
    public int pageSize = 5;

    public VacationHouseOwnerJpaService(VacationHouseOwnerRepository vacationHouseOwnerRepository) {
        this.vacationHouseOwnerRepository = vacationHouseOwnerRepository;
    }

    @Override
    public Page<VacationHouseOwner> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return vacationHouseOwnerRepository.findAll(pageable);
    }

    @Override
    public VacationHouseOwner findById(Long aLong) {
        return vacationHouseOwnerRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseOwner save(Long aLong, VacationHouseOwner object) {
        return vacationHouseOwnerRepository.save(object);
    }

    @Override
    public ArrayList<VacationHouseOwner> findAll() {
        return (ArrayList<VacationHouseOwner>) vacationHouseOwnerRepository.findAll();
    }

    @Override
    public void deleteById(Long aLong) {
        vacationHouseOwnerRepository.deleteById(aLong);
    }
}
