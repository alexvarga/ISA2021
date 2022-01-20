package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatComplaint;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface BoatComplaintService extends CrudService<BoatComplaint, Long> {

    Set<BoatComplaint> getAllByBoat(Boat boat);

    Set<BoatComplaint> getAllByUser(User user);

    void deleteById(Long id);

}
