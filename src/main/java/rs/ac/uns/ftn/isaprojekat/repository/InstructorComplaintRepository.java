package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.model.InstructorComplaint;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface InstructorComplaintRepository extends CrudRepository<InstructorComplaint, Long> {

    Set<InstructorComplaint> getAllByInstructor(Instructor instructor);

    Set<InstructorComplaint> getAllByUser(User user);

}
