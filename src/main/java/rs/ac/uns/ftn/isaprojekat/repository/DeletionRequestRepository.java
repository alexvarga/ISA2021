package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;

@Repository
public interface DeletionRequestRepository extends CrudRepository<DeletionRequest, Long> {



}
