package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.BoatOwner;

@Repository
public interface BoatOwnerRepository extends PagingAndSortingRepository<BoatOwner, Long> {
}
