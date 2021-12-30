package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface AdventureReservationRepository extends CrudRepository<AdventureReservation, Long> {
    Set<AdventureReservation> getAllByUser(User user);

    Set<AdventureReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time);

    Set<AdventureReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time);


}
