package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

import java.util.HashSet;
import java.util.Set;

@Profile("default")
@Service
public class VacationHouseJpaService implements VacationHouseService {

    private final VacationHouseRepository vacationHouseRepository;

    public VacationHouseJpaService(VacationHouseRepository vacationHouseRepository) {
        this.vacationHouseRepository = vacationHouseRepository;
    }

    @Override
    public Set<VacationHouse> findAll() {
        Set<VacationHouse> vacationHouses = new HashSet<>();
        vacationHouseRepository.findAll().forEach(vacationHouses::add);
        return vacationHouses;
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
