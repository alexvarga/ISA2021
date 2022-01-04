package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;
import rs.ac.uns.ftn.isaprojekat.repository.VacationHouseReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseReservationService;

import java.time.LocalDateTime;

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
}
