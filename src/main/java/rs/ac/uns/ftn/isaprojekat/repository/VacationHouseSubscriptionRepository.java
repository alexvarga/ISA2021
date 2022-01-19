package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseSubscription;

import java.util.Set;

@Repository
public interface VacationHouseSubscriptionRepository extends PagingAndSortingRepository<VacationHouseSubscription, Long> {

    Page<VacationHouseSubscription> findByUser(User user, Pageable pageable);

    Set<VacationHouseSubscription> findAllByUser(User user);

    Boolean existsByUserAndVacationHouse(User user, VacationHouse vacationHouse);

    VacationHouseSubscription findVacationHouseSubscriptionByVacationHouseAndUser(VacationHouse vacationHouse, User user);


}
