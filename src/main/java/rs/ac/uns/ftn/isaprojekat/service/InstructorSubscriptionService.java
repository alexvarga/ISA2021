package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.model.InstructorSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface InstructorSubscriptionService extends PageableCrudService<InstructorSubscription, Long> {

    Page<InstructorSubscription> findByUser(User user, int pageNumber, String sortField, String sortDirection);

    Set<InstructorSubscription> findAllByUser(User user);

    Boolean existsByUserAndInstructor(User user, Instructor instructor);

    InstructorSubscription findInstructorSubscriptionByInstructorAndUser(Instructor instructor, User user);
    Set<InstructorSubscription> findAllByInstructor(Instructor instructor);

}
