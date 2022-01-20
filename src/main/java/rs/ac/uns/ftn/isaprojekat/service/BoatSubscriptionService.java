package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface BoatSubscriptionService extends PageableCrudService<BoatSubscription, Long> {

    Page<BoatSubscription> findAllByUser(User user, int pageNumber, String sortField, String sortDirection);
    Set<BoatSubscription> findAllByUser(User user);

    Boolean existsByUserAndBoat(User user, Boat boat);

    Boolean existsByUser_IdAndBoat_Id(Long user_id, Long boat_id);

    BoatSubscription findBoatSubscriptionByBoatAndUser(Boat boat, User user);

    Set<BoatSubscription> findAllByBoat(Boat boat);

}
