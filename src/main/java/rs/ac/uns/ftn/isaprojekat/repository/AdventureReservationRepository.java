package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

@Repository
public interface AdventureReservationRepository extends PagingAndSortingRepository<AdventureReservation, Long> {
    Page<AdventureReservation> getAllByUser(User user, Pageable pageable);

    Page<AdventureReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, Pageable pageable);

    Page<AdventureReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, Pageable pageable);



    //sve aktuelne
    @Query(value="select ar from AdventureReservation ar where ar.user = ?1 and ar.dateEnd > ?2 and not(ar.reservationType ='DISCOUNTOFFER' or ar.reservationType='CANCELLED') ")
    Page<AdventureReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, Pageable pageable);

    //history ili otkazane
    @Query(value="select ar from AdventureReservation ar where (ar.user = ?1 and ar.dateEnd < ?2) and (not ar.reservationType ='DISCOUNTOFFER') or ar.reservationType='CANCELLED' ")
    Page<AdventureReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, Pageable pageable);

    Page<AdventureReservation> getAllByReservationType(ReservationType type, Pageable pageable);


    @Query("select case when count(ar.user.id)>0 then true else false end from AdventureReservation ar where ((?2 between ar.dateFrom and ar.dateEnd) or (?3 between ar.dateFrom and ar.dateEnd)) and ?1 = ar.user")
    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end);

}
