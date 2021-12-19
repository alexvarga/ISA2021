package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseOwner;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseOwnerRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseOwnerService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class VacationHouseOwnerJpaService implements VacationHouseOwnerService {

    private final VacationHouseOwnerRepository vacationHouseOwnerRepository;

    public VacationHouseOwnerJpaService(VacationHouseOwnerRepository vacationHouseOwnerRepository) {
        this.vacationHouseOwnerRepository = vacationHouseOwnerRepository;
    }

    @Override
    public Set<VacationHouseOwner> findAll() {
        Set<VacationHouseOwner> vacationHouseOwners = new HashSet<>();
        vacationHouseOwnerRepository.findAll().forEach(vacationHouseOwners::add);
        return vacationHouseOwners;
    }

    @Override
    public VacationHouseOwner findById(Long aLong) {
        return vacationHouseOwnerRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseOwner save(Long aLong, VacationHouseOwner object) {
        return vacationHouseOwnerRepository.save(object);
    }
}
