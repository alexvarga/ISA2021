package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.DeletionRequest;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.DeletionRequestRepository;
import rs.ac.uns.ftn.isaprojekat.service.DeletionRequestService;

import java.util.HashSet;
import java.util.Set;

@Service
public class DeletionRequestJpaService implements DeletionRequestService {

    private final DeletionRequestRepository deletionRequestRepository;

    public DeletionRequestJpaService(DeletionRequestRepository deletionRequestRepository) {
        this.deletionRequestRepository = deletionRequestRepository;
    }

    @Override
    public Set<DeletionRequest> findAll() {
        Set<DeletionRequest> requests = new HashSet<>();
        deletionRequestRepository.findAll().forEach(requests::add);
        return requests;
    }

    @Override
    public DeletionRequest findById(Long aLong) {
        return deletionRequestRepository.findById(aLong).orElse(null);
    }

    @Override
    public DeletionRequest save(Long aLong, DeletionRequest object) {
        return deletionRequestRepository.save(object);
    }

    @Override
    public void deleteById(Long id) {
        deletionRequestRepository.deleteById(id);
    }

    @Override
    public Set<DeletionRequest> findAllByUser(User user) {
        return deletionRequestRepository.findAllByUser(user);
    }
}
