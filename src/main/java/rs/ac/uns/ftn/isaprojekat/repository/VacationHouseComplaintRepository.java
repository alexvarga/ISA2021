package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseComplaint;

import java.util.Set;

@Repository
public interface VacationHouseComplaintRepository extends CrudRepository<VacationHouseComplaint, Long> {

    Set<VacationHouseComplaint> getAllByVacationHouse(VacationHouse house);

    Set<VacationHouseComplaint> getAllByUser(User user);

}
