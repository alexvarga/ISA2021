package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseSubscription;

import java.util.Set;

public interface VacationHouseSubscriptionService extends PageableCrudService<VacationHouseSubscription, Long> {

    Page<VacationHouseSubscription> findByUser(User user, int pageNumber, String sortField, String sortDirection);

    Set<VacationHouseSubscription> findAllByUser(User user);

    Boolean existsByUserAndVacationHouse(User user, VacationHouse vacationHouse);

    VacationHouseSubscription findVacationHouseSubscriptionByVacationHouseAndUser(VacationHouse vacationHouse, User user);
}