package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;

import java.time.LocalDateTime;


@Repository
public interface AdventureRepository extends PagingAndSortingRepository<Adventure, Long> {

    @Query(value="select a from Adventure a where a.id not in" +
            " (select ad.id from Adventure ad left join AdventureReservation ar on ad.id=ar.adventure.id where " +
            "(((?1 between ar.dateFrom and ar.dateEnd) or (?2 between ar.dateFrom and ar.dateEnd)) and(ar.reservationType = 'ACTIVE') ))")
    Page<Adventure> findAdventuresNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);
}
