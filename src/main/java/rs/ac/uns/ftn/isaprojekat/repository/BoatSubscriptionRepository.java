package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface BoatSubscriptionRepository extends PagingAndSortingRepository<BoatSubscription, Long> {

    Page<BoatSubscription> findAllByUser(User user, Pageable pageable);
    Set<BoatSubscription> findAllByUser(User user);

    Boolean existsByUserAndBoat(User user, Boat boat);

    Boolean existsByUser_IdAndBoat_Id(Long user_id, Long boat_id);

    BoatSubscription findBoatSubscriptionByBoatAndUser(Boat boat, User user);


}
