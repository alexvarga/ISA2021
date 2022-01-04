package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Boat;

import java.time.LocalDateTime;


@Repository
public interface BoatRepository extends PagingAndSortingRepository<Boat, Long> {



    @Query(value="select b from Boat b where b.id not in" +
            " (select bo.id from Boat bo left join BoatReservation br on bo.id=br.boat.id where " +
            "(((?1 between br.dateFrom and br.dateEnd) or (?2 between br.dateFrom and br.dateEnd)) and(br.reservationType = 'ACTIVE') ))")
    Page<Boat> findBoatsNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);
}
