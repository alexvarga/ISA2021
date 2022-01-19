package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseSubscription;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseSubscriptionRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseSubscriptionService;

import java.util.ArrayList;
import java.util.Set;

@Service
public class VacationHouseSubscriptionJpaService implements VacationHouseSubscriptionService {

    private final VacationHouseSubscriptionRepository vacationHouseSubscriptionRepository;
    private int itemsPerPage = 2;

    public VacationHouseSubscriptionJpaService(VacationHouseSubscriptionRepository vacationHouseSubscriptionRepository) {
        this.vacationHouseSubscriptionRepository = vacationHouseSubscriptionRepository;
    }


    @Override
    public Page<VacationHouseSubscription> findByUser(User user, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return vacationHouseSubscriptionRepository.findByUser(user, pageable);
    }

    @Override
    public Set<VacationHouseSubscription> findAllByUser(User user) {
        return vacationHouseSubscriptionRepository.findAllByUser(user);
    }

    @Override
    public Boolean existsByUserAndVacationHouse(User user, VacationHouse vacationHouse) {
        return vacationHouseSubscriptionRepository.existsByUserAndVacationHouse(user, vacationHouse);
    }

    @Override
    public VacationHouseSubscription findVacationHouseSubscriptionByVacationHouseAndUser(VacationHouse vacationHouse, User user) {
        return vacationHouseSubscriptionRepository.findVacationHouseSubscriptionByVacationHouseAndUser(vacationHouse, user);
    }

    @Override
    public Page<VacationHouseSubscription> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return vacationHouseSubscriptionRepository.findAll(pageable);
    }

    @Override
    public ArrayList<VacationHouseSubscription> findAll() {
        return (ArrayList<VacationHouseSubscription>) vacationHouseSubscriptionRepository.findAll();
    }

    @Override
    public VacationHouseSubscription findById(Long aLong) {
        return vacationHouseSubscriptionRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseSubscription save(Long aLong, VacationHouseSubscription object) {
        return vacationHouseSubscriptionRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vacationHouseSubscriptionRepository.deleteById(aLong);
    }
}
