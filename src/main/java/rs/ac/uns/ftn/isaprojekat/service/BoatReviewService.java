package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;

public interface BoatReviewService extends PageableCrudService<BoatReview, Long> {

    Page<BoatReview> getAllByBoat(Boat boat, int pageNumber, String sortField, String sortDirection);

    Set<BoatReview> getAllByBoat_id(Long boat_id);

    Set<BoatReview> getAllByBoatAndReviewStatus(Boat boat, ReviewStatus status);

    Set<BoatReview> getAllByReviewStatus(ReviewStatus status);

    Page<BoatReview> getAllByBoatAndReviewStatus(Boat boat, ReviewStatus status, int pageNumber, String sortField, String sortDirection);

    Set<BoatReview> getAllByUser(User user);
}
