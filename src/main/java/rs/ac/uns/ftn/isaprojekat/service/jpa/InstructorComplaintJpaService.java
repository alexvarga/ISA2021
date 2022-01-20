package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.model.InstructorComplaint;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.InstructorComplaintRepository;
import rs.ac.uns.ftn.isaprojekat.service.InstructorComplaintService;

import java.util.HashSet;
import java.util.Set;

@Service
public class InstructorComplaintJpaService implements InstructorComplaintService {

    private final InstructorComplaintRepository instructorComplaintRepository;

    public InstructorComplaintJpaService(InstructorComplaintRepository instructorComplaintRepository) {
        this.instructorComplaintRepository = instructorComplaintRepository;
    }

    @Override
    public Set<InstructorComplaint> getAllByInstructor(Instructor instructor) {
        return instructorComplaintRepository.getAllByInstructor(instructor);
    }

    @Override
    public Set<InstructorComplaint> getAllByUser(User user) {
        return instructorComplaintRepository.getAllByUser(user);
    }

    @Override
    public void deleteById(Long id) {
        instructorComplaintRepository.deleteById(id);
    }

    @Override
    public Set<InstructorComplaint> findAll() {
        Set<InstructorComplaint> complaints = new HashSet<>();
        instructorComplaintRepository.findAll().forEach(complaints::add);
        return complaints;
    }

    @Override
    public InstructorComplaint findById(Long aLong) {
        return instructorComplaintRepository.findById(aLong).orElse(null);
    }

    @Override
    public InstructorComplaint save(Long aLong, InstructorComplaint object) {
        return instructorComplaintRepository.save(object);
    }
}
