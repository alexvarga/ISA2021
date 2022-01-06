package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.User;

import java.time.LocalDateTime;

@Repository
public interface BoatReservationRepository extends PagingAndSortingRepository<BoatReservation, Long> {

    //@Query(value="select br from BoatReservation br where br.user=?1")
    Page<BoatReservation> getAllByUser(User user, Pageable pageable);
    //Page<BoatReservation> findAllByUser(User user, Pageable pageable);

    //@Query(value="select br from BoatReservation br where br.user=?1 ")
    Page<BoatReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, Pageable pageable);

    //@Query(value="select br from BoatReservation br where br.user=?1 ")
    Page<BoatReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, Pageable pageable);

    @Query("select case when count(br.user.id)>0 then true else false end from BoatReservation br where ((?2 between br.dateFrom and br.dateEnd) or (?3 between br.dateFrom and br.dateEnd)) and ?1 = br.user")
    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end);

}
