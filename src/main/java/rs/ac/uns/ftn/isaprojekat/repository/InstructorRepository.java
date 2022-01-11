package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;

@Repository
public interface InstructorRepository extends PagingAndSortingRepository<Instructor, Long> {
}
