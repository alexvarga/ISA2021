package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.InstructorSubscription;

@Repository
public interface InstructorSubscriptionRepository extends PagingAndSortingRepository<InstructorSubscription, Long> {
}
