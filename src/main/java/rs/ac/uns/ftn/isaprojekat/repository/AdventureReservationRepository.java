package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

@Repository
public interface AdventureReservationRepository extends PagingAndSortingRepository<AdventureReservation, Long> {
    Page<AdventureReservation> getAllByUser(User user, Pageable pageable);

    Page<AdventureReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, Pageable pageable);

    Page<AdventureReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, Pageable pageable);


}
