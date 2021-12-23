package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.repository.InstructorRepository;
import rs.ac.uns.ftn.isaprojekat.service.InstructorService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("default")
public class InstructorJpaService implements InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorJpaService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Set<Instructor> findAll() {
        Set<Instructor> instructors = new HashSet<>();
        instructorRepository.findAll().forEach(instructors::add);
        return instructors;
    }

    @Override
    public Instructor findById(Long aLong) {
        return instructorRepository.findById(aLong).orElse(null);
    }

    @Override //TODO just object
    public Instructor save(Long aLong, Instructor object) {

        return instructorRepository.save(object);
    }
}
