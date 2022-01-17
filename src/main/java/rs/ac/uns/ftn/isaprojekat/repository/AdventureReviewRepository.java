package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReview;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;

import java.util.Set;

@Repository
public interface AdventureReviewRepository extends PagingAndSortingRepository<AdventureReview, Long> {

    Page<AdventureReview> getAllByAdventure(Adventure adventure, Pageable pageable);

    Page<AdventureReview> getAllByAdventureAndReviewStatus(Adventure adventure, ReviewStatus status,  Pageable pageable);

    Set<AdventureReview> getAllByAdventure_Id(Long adventure_id);

    Set<AdventureReview> getAllByAdventureAndReviewStatus(Adventure adventure, ReviewStatus status);

    Set<AdventureReview> getAllByReviewStatus(ReviewStatus status);

}
