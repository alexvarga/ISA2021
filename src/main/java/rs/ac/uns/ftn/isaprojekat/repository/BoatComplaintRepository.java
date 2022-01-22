package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatComplaint;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface BoatComplaintRepository extends CrudRepository<BoatComplaint, Long> {

    Set<BoatComplaint> getAllByBoat(Boat boat);

    Set<BoatComplaint> getAllByUser(User user);

}
