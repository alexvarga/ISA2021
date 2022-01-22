package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.util.Set;


public interface AdventureReviewService extends PageableCrudService<AdventureReview, Long> {

    Page<AdventureReview> getAllByAdventure(Adventure adventure, int pageNumber, String sortField, String sortDirection);

    Page<AdventureReview> getAllByAdventureAndReviewStatus(Adventure adventure, ReviewStatus status, int pageNumber, String sortField, String sortDirection);

    Set<AdventureReview> getAllByAdventure_Id(Long adventure_id);

    Set<AdventureReview> getAllByAdventureAndReviewStatus(Adventure adventure, ReviewStatus status);

    Set<AdventureReview> getAllByReviewStatus(ReviewStatus status);

    Set<AdventureReview> getAllByUser(User user);


}
