package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface BoatReservationRepository extends CrudRepository<BoatReservation, Long> {

    Set<BoatReservation> getAllByUser(User user);

}
