package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseComplaint;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseComplaintRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseComplaintService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("default")
public class VacationHouseComplaintJpaService implements VacationHouseComplaintService {

    private final VacationHouseComplaintRepository vacationHouseComplaintRepository;

    public VacationHouseComplaintJpaService(VacationHouseComplaintRepository vacationHouseComplaintRepository) {
        this.vacationHouseComplaintRepository = vacationHouseComplaintRepository;
    }

    @Override
    public Set<VacationHouseComplaint> getAllByVacationHouse(VacationHouse house) {
        return vacationHouseComplaintRepository.getAllByVacationHouse(house);
    }

    @Override
    public Set<VacationHouseComplaint> getAllByUser(User user) {
        return vacationHouseComplaintRepository.getAllByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        vacationHouseComplaintRepository.deleteById(id);
    }

    @Override
    public Set<VacationHouseComplaint> findAll() {
        Set<VacationHouseComplaint> complaints = new HashSet<>();
        vacationHouseComplaintRepository.findAll().forEach(complaints::add);
        return complaints;
    }

    @Override
    public VacationHouseComplaint findById(Long aLong) {
        return vacationHouseComplaintRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseComplaint save(Long aLong, VacationHouseComplaint object) {
        return vacationHouseComplaintRepository.save( object);
    }

}
