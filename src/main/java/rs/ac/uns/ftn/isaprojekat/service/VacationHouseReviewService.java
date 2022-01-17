package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReview;

import java.util.Set;

public interface VacationHouseReviewService extends PageableCrudService<VacationHouseReview, Long> {

    Page<VacationHouseReview> getAllByVacationHouse(VacationHouse vacationHouse, int pageNumber, String sortField, String sortDirection);

    Page<VacationHouseReview> getAllByVacationHouseAndReviewStatus(VacationHouse vacationHouse, ReviewStatus status, int pageNumber, String sortField, String sortDirection);

    Set<VacationHouseReview> getAllByVacationHouseAndReviewStatus(VacationHouse vacationHouse, ReviewStatus status);

    Set<VacationHouseReview> getAllByReviewStatus(ReviewStatus status);

    Set<VacationHouseReview> getAllByVacationHouse_Id(Long id);
}
