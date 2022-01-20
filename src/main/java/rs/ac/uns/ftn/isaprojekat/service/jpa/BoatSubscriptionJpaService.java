package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.BoatSubscriptionRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatSubscriptionService;

import java.util.ArrayList;
import java.util.Set;

@Service
public class BoatSubscriptionJpaService implements BoatSubscriptionService {


    private final BoatSubscriptionRepository boatSubscriptionRepository;
    private int itemsPerPage = 2;

    public BoatSubscriptionJpaService(BoatSubscriptionRepository boatSubscriptionRepository) {
        this.boatSubscriptionRepository = boatSubscriptionRepository;
    }

    @Override
    public Page<BoatSubscription> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return boatSubscriptionRepository.findAll(pageable);
    }

    @Override
    public ArrayList<BoatSubscription> findAll() {
        return (ArrayList<BoatSubscription>) boatSubscriptionRepository.findAll();
    }

    @Override
    public BoatSubscription findById(Long aLong) {
        return boatSubscriptionRepository.findById(aLong).orElse(null);
    }

    @Override
    public BoatSubscription save(Long aLong, BoatSubscription object) {
        return boatSubscriptionRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        boatSubscriptionRepository.deleteById(aLong);
    }

    @Override
    public Page<BoatSubscription> findAllByUser(User user, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return boatSubscriptionRepository.findAllByUser(user, pageable);
    }

    @Override
    public Set<BoatSubscription> findAllByUser(User user) {
        return boatSubscriptionRepository.findAllByUser(user);
    }

    @Override
    public Boolean existsByUserAndBoat(User user, Boat boat) {
        return boatSubscriptionRepository.existsByUserAndBoat(user, boat);
    }

    @Override
    public Boolean existsByUser_IdAndBoat_Id(Long user_id, Long boat_id) {
        return boatSubscriptionRepository.existsByUser_IdAndBoat_Id(user_id, boat_id);
    }

    @Override
    public BoatSubscription findBoatSubscriptionByBoatAndUser(Boat boat, User user) {
        return boatSubscriptionRepository.findBoatSubscriptionByBoatAndUser(boat, user);
    }

    @Override
    public Set<BoatSubscription> findAllByBoat(Boat boat) {
        return boatSubscriptionRepository.findAllByBoat(boat);
    }
}
