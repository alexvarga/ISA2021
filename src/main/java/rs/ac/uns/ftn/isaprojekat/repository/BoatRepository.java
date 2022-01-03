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



    @Query(value="select distinct b from Boat b left join BoatReservation br on b.id=br.boat.id where ?1< br.dateFrom and ?2 < br.dateFrom or ?1> br.dateEnd and ?2 > br.dateEnd or br.dateFrom is null")
    Page<Boat> findBoatsNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);
}
