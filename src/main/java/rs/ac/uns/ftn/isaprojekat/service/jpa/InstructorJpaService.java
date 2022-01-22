package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.repository.InstructorRepository;
import rs.ac.uns.ftn.isaprojekat.service.InstructorService;

import java.util.ArrayList;

@Service
@Profile("default")
public class InstructorJpaService implements InstructorService {

    private final InstructorRepository instructorRepository;
    public int pageSize = 2;

    public InstructorJpaService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public Page<Instructor> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        return instructorRepository.findAll(pageable);
    }

    @Override
    public Instructor findById(Long aLong) {
        return instructorRepository.findById(aLong).orElse(null);
    }

    @Override //TODO just object
    public Instructor save(Long aLong, Instructor object) {

        return instructorRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        instructorRepository.deleteById(aLong);
    }

    @Override
    public ArrayList<Instructor> findAll() {
        return null;
    }
}
