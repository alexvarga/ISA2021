package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

@Profile("default")
@Service
public class VacationHouseReservationJpaService implements VacationHouseReservationService {

    private int itemsPerPage = 2;
    private final VacationHouseReservationRepository vacationHouseReservationRepository;

    public VacationHouseReservationJpaService(VacationHouseReservationRepository vacationHouseReservationRepository) {
        this.vacationHouseReservationRepository = vacationHouseReservationRepository;
    }

    @Override
    public Page<VacationHouseReservation> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort); //zero based index


        return vacationHouseReservationRepository.findAll(pageable);
    }

    @Override
    public VacationHouseReservation findById(Long aLong) {
        return vacationHouseReservationRepository.findById(aLong).orElse(null);
    }

    @Override
    public VacationHouseReservation save(Long aLong, VacationHouseReservation object) {
        return vacationHouseReservationRepository.save(object);
    }

    @Override
    public Page<VacationHouseReservation> getAllByUser(User user, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReservationRepository.getAllByUser(user, pageable);
    }

    @Override
    public Page<VacationHouseReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReservationRepository.getAllByUserAndDateEndBefore(user, time, pageable);
    }

    @Override
    public Page<VacationHouseReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReservationRepository.getAllByUserAndDateEndAfter(user, time, pageable);

    }

    @Override
    public boolean existsByUser(User user, LocalDateTime start, LocalDateTime end, Long id) {
       return vacationHouseReservationRepository.existsByUser(user, start, end, id);
    }

    @Override
    public Page<VacationHouseReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReservationRepository.getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(user, time, pageable);
    }

    @Override
    public Page<VacationHouseReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReservationRepository.getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(user, time, pageable);
    }

    @Override
    public Page<VacationHouseReservation> getAllByReservationType(ReservationType type, int pageNumber, String sortField, String sortDirection) {
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return vacationHouseReservationRepository.getAllByReservationType(type, pageable);
    }

    @Override
    public void deleteById(Long aLong) {
        vacationHouseReservationRepository.deleteById(aLong);
    }

    @Override
    public ArrayList<VacationHouseReservation> findAll() {
        return (ArrayList<VacationHouseReservation>) vacationHouseReservationRepository.findAll();
    }

    @Override
    public Set<VacationHouseReservation> getAllByVacationHouse_Id(Long vacationHouse_id) {
        return vacationHouseReservationRepository.getAllByVacationHouse_Id(vacationHouse_id);
    }

    @Override
    public Set<VacationHouseReservation> getAllByUser(User user) {
        return vacationHouseReservationRepository.getAllByUser(user);
    }
}
