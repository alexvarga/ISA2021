package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.ReviewStatus;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReview;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseReviewRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReviewService;

import java.util.ArrayList;
import java.util.Set;

@Service
public class VacationHouseReviewJpaService implements VacationHouseReviewService {

    private final VacationHouseReviewRepository vacationHouseReviewRepository;
    private int itemsPerPage = 2;

    public VacationHouseReviewJpaService(VacationHouseReviewRepository vacationHouseReviewRepository) {
        this.vacationHouseReviewRepository = vacationHouseReviewRepository;
    }

    @Override
    public Page<VacationHouseReview> getAllByVacationHouse(VacationHouse vacationHouse, int pageNumber,
                                                           String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return vacationHouseReviewRepository.getAllByVacationHouse(vacationHouse, pageable);
    }

    @Override
    public Page<VacationHouseReview> getAllByVacationHouseAndReviewStatus(VacationHouse vacationHouse,
                                                                          ReviewStatus status, int pageNumber,
                                                                          String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReviewRepository.getAllByVacationHouseAndReviewStatus(vacationHouse, status, pageable);
    }

    @Override
    public Set<VacationHouseReview> getAllByVacationHouseAndReviewStatus(VacationHouse vacationHouse,
                                                                         ReviewStatus status) {
        return vacationHouseReviewRepository.getAllByVacationHouseAndReviewStatus(vacationHouse, status);
    }

    @Override
    public Set<VacationHouseReview> getAllByReviewStatus(ReviewStatus status) {
        return vacationHouseReviewRepository.getAllByReviewStatus(status);
    }

    @Override
    public Set<VacationHouseReview> getAllByVacationHouse_Id(Long id) {
        return vacationHouseReviewRepository.getAllByVacationHouse_Id(id);
    }

    @Override
    public Page<VacationHouseReview> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        return vacationHouseReviewRepository.findAll(pageable);
    }

    @Override
    public ArrayList<VacationHouseReview> findAll() {
        return (ArrayList<VacationHouseReview>) vacationHouseReviewRepository.findAll();
    }

    @Override
    public VacationHouseReview findById(Long aLong) {
        return vacationHouseReviewRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseReview save(Long aLong, VacationHouseReview object) {
        return vacationHouseReviewRepository.save(object);
    }

    @Override
    public void deleteById(Long aLong) {
        vacationHouseReviewRepository.deleteById(aLong);

    }

    @Override
    public Set<VacationHouseReview> getAllByUser(User user) {
        return vacationHouseReviewRepository.getAllByUser(user);
    }
}
