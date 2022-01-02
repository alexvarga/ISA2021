package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;


@Repository
public interface AdventureRepository extends PagingAndSortingRepository<Adventure, Long> {
}
