package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;

import java.time.LocalDateTime;

@Repository
public interface VacationHouseReservationRepository extends PagingAndSortingRepository<VacationHouseReservation, Long> {
    Page<VacationHouseReservation> getAllByUser(User user, Pageable pageable);

    Page<VacationHouseReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, Pageable pageable);

    Page<VacationHouseReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time, Pageable pageable);

}
