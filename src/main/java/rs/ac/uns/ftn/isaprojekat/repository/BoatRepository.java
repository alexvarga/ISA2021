package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Boat;


@Repository
public interface BoatRepository extends CrudRepository<Boat, Long> {
}
