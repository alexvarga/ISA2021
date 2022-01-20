package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

@Repository
public interface BoatReviewRepository extends PagingAndSortingRepository<BoatReview, Long> {

    Page<BoatReview> getAllByBoat(Boat boat, Pageable pageable);

    Set<BoatReview> getAllByBoat_id(Long boat_id);

    Set<BoatReview> getAllByBoatAndReviewStatus(Boat boat, ReviewStatus status);

    Set<BoatReview> getAllByReviewStatus(ReviewStatus status);

    Page<BoatReview> getAllByBoatAndReviewStatus(Boat boat, ReviewStatus status, Pageable pageable);

    Set<BoatReview> getAllByUser(User user);

}
