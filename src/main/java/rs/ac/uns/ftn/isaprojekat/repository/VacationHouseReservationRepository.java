package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface VacationHouseReservationRepository extends CrudRepository<VacationHouseReservation, Long> {
    Set<VacationHouseReservation> getAllByUser(User user);

    Set<VacationHouseReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time);

    Set<VacationHouseReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time);

}
