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

    @Query(value="select distinct a from Adventure a left join AdventureReservation ar on a.id=ar.adventure.id where ?1< ar.dateFrom and ?2 < ar.dateFrom or ?1> ar.dateEnd and ?2 > ar.dateEnd or ar.dateFrom is null")
    Page<Adventure> findAdventuresNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);
}
