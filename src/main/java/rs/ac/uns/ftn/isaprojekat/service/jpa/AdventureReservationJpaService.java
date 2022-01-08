package rs.ac.uns.ftn.isaprojekat.service.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.repository.AdventureReservationRepository;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;

import java.time.LocalDateTime;

@Profile("default")
@Service
public class AdventureReservationJpaService implements AdventureReservationService {

    private int itemsPerPage = 2;

    private final AdventureReservationRepository adventureReservationRepository;

    public AdventureReservationJpaService(AdventureReservationRepository adventureReservationRepository) {
        this.adventureReservationRepository = adventureReservationRepository;
    }

    @Override
    public Page<AdventureReservation> findAll(int pageNumber, String sortField, String sortDirection) {
        Sort sort;
        if (sortDirection.equals("asc")){
            sort = Sort.by(sortField).ascending();
        }else{
            sort = Sort.by(sortField).descending();
        }

        Pageable pageable = PageRequest.of(pageNumber-1, 2, sort); //zero based index


        return adventureReservationRepository.findAll(pageable);
    }

    @Override
    public AdventureReservation findById(Long aLong) {
        return adventureReservationRepository.findById(aLong).orElse(null);
    }

    @Override
    public AdventureReservation save(Long aLong, AdventureReservation object) {
        return adventureReservationRepository.save(object);
    }

    @Override
    public Page<AdventureReservation> getAllByUser(User user, int pageNumber, String sortField, String sortDirection) {
//        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
//        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);
        Pageable pageable = sortAndPage(pageNumber, sortField, sortDirection);

        return adventureReservationRepository.getAllByUser(user, pageable);
    }

    @Override
    public Page<AdventureReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Pageable pageable = sortAndPage(pageNumber, sortField, sortDirection);

        return adventureReservationRepository.getAllByUserAndDateEndBefore(user, time, pageable);
    }

    @Override
    public Page<AdventureReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Pageable pageable = sortAndPage(pageNumber, sortField, sortDirection);

        return adventureReservationRepository.getAllByUserAndDateEndAfter(user, time, pageable);
    }


    private Pageable sortAndPage(int pageNumber, String sortField, String sortDirection){
        Sort sort = sortDirection.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber-1, itemsPerPage, sort);

        return pageable;
    }


    @Override
    public boolean existsByUser(User user, LocalDateTime start, LocalDateTime end) {

        return adventureReservationRepository.existsByUser(user, start, end);
    }

    @Override
    public Page<AdventureReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Pageable pageable = sortAndPage(pageNumber, sortField, sortDirection);

        return adventureReservationRepository.getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(user, time, pageable);
    }

    @Override
    public Page<AdventureReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, int pageNumber, String sortField, String sortDirection) {
        Pageable pageable = sortAndPage(pageNumber, sortField, sortDirection);

        return adventureReservationRepository.getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(user, time, pageable);
    }

    @Override
    public Page<AdventureReservation> getAllByReservationType(ReservationType type, int pageNumber, String sortField, String sortDirection) {
        Pageable pageable = sortAndPage(pageNumber, sortField, sortDirection);

        return adventureReservationRepository.getAllByReservationType(type, pageable);
    }
}
