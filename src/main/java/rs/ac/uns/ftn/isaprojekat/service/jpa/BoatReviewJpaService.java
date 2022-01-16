package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.repository.BoatReviewRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatReviewService;

import java.util.ArrayList;
import java.util.Set;

@Service
public class BoatReviewJpaService implements BoatReviewService {

    private final BoatReviewRepository boatReviewRepository;
    private int itemsPerPage = 2;


    public BoatReviewJpaService(BoatReviewRepository boatReviewRepository) {
        this.boatReviewRepository = boatReviewRepository;
    }

    @Override
    public Page<BoatReview> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return boatReviewRepository.findAll(pageable);
    }

    @Override
    public ArrayList<BoatReview> findAll() {
        return (ArrayList<BoatReview>) boatReviewRepository.findAll();
    }

    @Override
    public BoatReview findById(Long aLong) {
        return boatReviewRepository.findById(aLong).orElse(null);
    }

    @Override
    public BoatReview save(Long aLong, BoatReview object) {
        return boatReviewRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        boatReviewRepository.deleteById(aLong);

    }

    @Override
    public Page<BoatReview> getAllByBoat(Boat boat, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return boatReviewRepository.getAllByBoat(boat, pageable);
    }

    @Override
    public Set<BoatReview> getAllByBoat_id(Long boat_id) {
        return boatReviewRepository.getAllByBoat_id(boat_id);
    }

    @Override
    public Set<BoatReview> getAllByBoatAndReviewStatus(Boat boat, ReviewStatus status) {
        return boatReviewRepository.getAllByBoatAndReviewStatus(boat, status);
    }

    @Override
    public Page<BoatReview> getAllByBoatAndReviewStatus(Boat boat, ReviewStatus status, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return boatReviewRepository.getAllByBoatAndReviewStatus(boat, status, pageable);
    }

    @Override
    public Set<BoatReview> getAllByReviewStatus(ReviewStatus status) {
        return boatReviewRepository.getAllByReviewStatus(status);
    }
}
