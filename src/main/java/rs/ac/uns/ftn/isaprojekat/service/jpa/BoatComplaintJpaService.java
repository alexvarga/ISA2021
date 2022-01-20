package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatComplaint;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.BoatComplaintRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatComplaintService;

import java.util.HashSet;
import java.util.Set;

@Service
public class BoatComplaintJpaService implements BoatComplaintService {

    private final BoatComplaintRepository boatComplaintRepository;

    public BoatComplaintJpaService(BoatComplaintRepository boatComplaintRepository) {
        this.boatComplaintRepository = boatComplaintRepository;
    }

    @Override
    public Set<BoatComplaint> getAllByBoat(Boat boat) {
        return boatComplaintRepository.getAllByBoat(boat);
    }

    @Override
    public Set<BoatComplaint> getAllByUser(User user) {
        return boatComplaintRepository.getAllByUser(user);
    }

    @Override
    public Set<BoatComplaint> findAll() {
        Set<BoatComplaint> complaints = new HashSet<>();
        boatComplaintRepository.findAll().forEach(complaints::add);
        return complaints;

    }

    @Override
    public BoatComplaint findById(Long aLong) {
        return boatComplaintRepository.findById(aLong).orElse(null);
    }

    @Override
    public BoatComplaint save(Long aLong, BoatComplaint object) {
        return boatComplaintRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        boatComplaintRepository.deleteById(id);
    }
}
