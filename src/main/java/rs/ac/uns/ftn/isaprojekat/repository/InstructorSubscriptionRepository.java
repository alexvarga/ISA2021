package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.model.InstructorSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface InstructorSubscriptionRepository extends PagingAndSortingRepository<InstructorSubscription, Long> {

    Page<InstructorSubscription> findByUser(User user, Pageable pageable);

    Set<InstructorSubscription> findAllByUser(User user);
    Set<InstructorSubscription> findAllByInstructor(Instructor instructor);

    Boolean existsByUserAndInstructor(User user, Instructor instructor);

    InstructorSubscription findInstructorSubscriptionByInstructorAndUser(Instructor instructor, User user);


}
