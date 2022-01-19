package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.model.InstructorSubscription;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.InstructorSubscriptionRepository;
import rs.ac.uns.ftn.isaprojekat.service.InstructorSubscriptionService;

import java.util.ArrayList;
import java.util.Set;

@Service
public class InstructorSubscriptionJpaService implements InstructorSubscriptionService {

    private final InstructorSubscriptionRepository instructorSubscriptionRepository;
    private int itemsPerPage = 5;

    public InstructorSubscriptionJpaService(InstructorSubscriptionRepository instructorSubscriptionRepository) {
        this.instructorSubscriptionRepository = instructorSubscriptionRepository;
    }

    @Override
    public Page<InstructorSubscription> findByUser(User user, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return instructorSubscriptionRepository.findByUser(user, pageable);
    }

    @Override
    public Set<InstructorSubscription> findAllByUser(User user) {
        return instructorSubscriptionRepository.findAllByUser(user);
    }

    @Override
    public Boolean existsByUserAndInstructor(User user, Instructor instructor) {
        return instructorSubscriptionRepository.existsByUserAndInstructor(user, instructor);
    }

    @Override
    public InstructorSubscription findInstructorSubscriptionByInstructorAndUser(Instructor instructor, User user) {
        return instructorSubscriptionRepository.findInstructorSubscriptionByInstructorAndUser(instructor, user);
    }

    @Override
    public Page<InstructorSubscription> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return instructorSubscriptionRepository.findAll(pageable);
    }

    @Override
    public ArrayList<InstructorSubscription> findAll() {
        return (ArrayList<InstructorSubscription>) instructorSubscriptionRepository.findAll();
    }

    @Override
    public InstructorSubscription findById(Long aLong) {
        return instructorSubscriptionRepository.findById(aLong).orElse(null);
    }

    @Override
    public InstructorSubscription save(Long aLong, InstructorSubscription object) {
        return instructorSubscriptionRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        instructorSubscriptionRepository.deleteById(aLong);

    }
}
