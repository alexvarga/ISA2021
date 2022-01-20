package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.BoatReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;


@Profile("default")
@Service
public class BoatReservationJpaService implements BoatReservationService {

    private final BoatReservationRepository boatReservationRepository;

    public BoatReservationJpaService(BoatReservationRepository boatReservationRepository) {
        this.boatReservationRepository = boatReservationRepository;
    }

    //TODO sort direction separate function

    @Override
    public Page<BoatReservation> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort); //zero based index
        return boatReservationRepository.findAll(pageable);
    }

    @Override
    public BoatReservation findById(Long aLong) {
        return boatReservationRepository.findById(aLong).orElse(null);
    }

    @Override
    public BoatReservation save(Long aLong, BoatReservation object) {
        return boatReservationRepository.save(object);
    }

    @Override
    public Page<BoatReservation> getAllByUser(User user, int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return boatReservationRepository.getAllByUser(user, pageable);
    }

    @Override
    public Page<BoatReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return boatReservationRepository.getAllByUserAndDateEndBefore(user, time, pageable);
    }

    @Override
    public Page<BoatReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return boatReservationRepository.getAllByUserAndDateEndAfter(user, time, pageable);
    }

    @Override
    public boolean existsByUser(User user, LocalDateTime start, LocalDateTime end, Long id ) {
        return boatReservationRepository.existsByUser(user, start, end, id);
    }

    @Override
    public Page<BoatReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return boatReservationRepository.getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(user, time, pageable);
    }

    @Override
    public Page<BoatReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return boatReservationRepository.getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(user, time, pageable);
    }

    @Override
    public Page<BoatReservation> getAllByReservationType(ReservationType type, int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber - 1, 2, sort);
        return boatReservationRepository.getAllByReservationType(type, pageable);
    }

    @Override
    public void deleteById(Long aLong) {
        boatReservationRepository.deleteById(aLong);
    }

    @Override
    public ArrayList<BoatReservation> findAll() {
        return (ArrayList<BoatReservation>) boatReservationRepository.findAll();
    }


    @Override
    public Set<BoatReservation> getAllByBoat_Id(Long boat_id) {
        return boatReservationRepository.getAllByBoat_Id(boat_id);
    }

    @Override
    public Set<BoatReservation> getAllByUser(User user) {
        return boatReservationRepository.getAllByUser(user);
    }
}
