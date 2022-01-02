package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<BoatReservation> getAllByUserAndDateFromAfter(User user, LocalDateTime time, Pageable pageable);

}
