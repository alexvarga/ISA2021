package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseOwner;

@Repository
public interface VacationHouseOwnerRepository extends PagingAndSortingRepository<VacationHouseOwner, Long> {
}
