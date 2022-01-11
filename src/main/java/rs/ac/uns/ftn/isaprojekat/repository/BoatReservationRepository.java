package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface BoatReservationRepository extends PagingAndSortingRepository<BoatReservation, Long> {

    Page<BoatReservation> getAllByUser(User user, Pageable pageable);

    Page<BoatReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, Pageable pageable);

    Page<BoatReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, Pageable pageable);

    Set<BoatReservation> getAllByBoat_Id(Long boat_id);


    @Query(value="select br from BoatReservation br where (br.user = ?1 and br.dateEnd > ?2) and not(br.reservationType ='DISCOUNTOFFER' or br.reservationType='CANCELLED') ")
    Page<BoatReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, Pageable pageable);

    @Query(value="select br from BoatReservation br where (br.user = ?1 and br.dateEnd < ?2) and (not br.reservationType ='DISCOUNTOFFER') or (br.reservationType='CANCELLED') ")
    Page<BoatReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, Pageable pageable);

    Page<BoatReservation> getAllByReservationType(ReservationType type, Pageable pageable);


    @Query("select case when count(br.user.id)>0 then true else false end from BoatReservation br where ((?2 between br.dateFrom and br.dateEnd) or (?3 between br.dateFrom and br.dateEnd) or (?2<br.dateFrom and ?3>br.dateEnd) ) and ?1 = br.user and ?4=br.boat.id")
    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end, Long id);

}
