package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface DeletionRequestRepository extends CrudRepository<DeletionRequest, Long> {

    Set<DeletionRequest> findAllByUser(User user);


}
