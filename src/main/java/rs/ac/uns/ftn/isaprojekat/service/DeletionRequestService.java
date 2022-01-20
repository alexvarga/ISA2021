package rs.ac.uns.ftn.isaprojekat.service;

import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface DeletionRequestService extends CrudService<DeletionRequest, Long> {

    void deleteById(Long id);

    Set<DeletionRequest> findAllByUser(User user);


}
