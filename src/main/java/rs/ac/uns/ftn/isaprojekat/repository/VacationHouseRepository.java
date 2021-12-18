package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;

@Repository
public interface VacationHouseRepository extends CrudRepository<VacationHouse, Long> {
}
