package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.AdventureReviewRepository;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReviewService;

import java.util.ArrayList;
import java.util.Set;

@Service
public class AdventureReviewJpaService implements AdventureReviewService {

    private final AdventureReviewRepository adventureReviewRepository;
    private int itemsPerPage = 5;

    public AdventureReviewJpaService(AdventureReviewRepository adventureReviewRepository) {
        this.adventureReviewRepository = adventureReviewRepository;
    }

    @Override
    public Page<AdventureReview> getAllByAdventure(Adventure adventure, int pageNumber, String sortField,
                                                   String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return adventureReviewRepository.getAllByAdventure(adventure, pageable);
    }

    @Override
    public Page<AdventureReview> getAllByAdventureAndReviewStatus(Adventure adventure, ReviewStatus status,
                                                                  int pageNumber, String sortField,
                                                                  String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return adventureReviewRepository.getAllByAdventureAndReviewStatus(adventure, status, pageable);
    }

    @Override
    public Set<AdventureReview> getAllByAdventure_Id(Long adventure_id) {
        return adventureReviewRepository.getAllByAdventure_Id(adventure_id);
    }

    @Override
    public Set<AdventureReview> getAllByAdventureAndReviewStatus(Adventure adventure, ReviewStatus status) {
        return adventureReviewRepository.getAllByAdventureAndReviewStatus(adventure, status);
    }

    @Override
    public Set<AdventureReview> getAllByReviewStatus(ReviewStatus status) {
        return adventureReviewRepository.getAllByReviewStatus(status);
    }

    @Override
    public Page<AdventureReview> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return adventureReviewRepository.findAll(pageable);
    }

    @Override
    public ArrayList<AdventureReview> findAll() {
        return (ArrayList<AdventureReview>) adventureReviewRepository.findAll();
    }

    @Override
    public AdventureReview findById(Long aLong) {
        return adventureReviewRepository.findById(aLong).orElse(null);
    }

    @Override
    public AdventureReview save(Long aLong, AdventureReview object) {
        return adventureReviewRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        adventureReviewRepository.deleteById(aLong);
    }

    @Override
    public Set<AdventureReview> getAllByUser(User user) {
        return adventureReviewRepository.getAllByUser(user);
    }
}
