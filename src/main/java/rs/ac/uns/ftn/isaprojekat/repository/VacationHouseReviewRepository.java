package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReview;

import java.util.Set;

@Repository
public interface VacationHouseReviewRepository extends PagingAndSortingRepository<VacationHouseReview, Long> {

    Page<VacationHouseReview> getAllByVacationHouse(VacationHouse vacationHouse, Pageable pageable);

    Page<VacationHouseReview> getAllByVacationHouseAndReviewStatus(VacationHouse vacationHouse, ReviewStatus status, Pageable pageable);

    Set<VacationHouseReview> getAllByVacationHouseAndReviewStatus(VacationHouse vacationHouse, ReviewStatus status);

    Set<VacationHouseReview> getAllByReviewStatus(ReviewStatus status);

    Set<VacationHouseReview> getAllByVacationHouse_Id(Long id);

}
