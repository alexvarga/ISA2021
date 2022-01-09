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
            "(((?1 between ar.dateFrom and ar.dateEnd) or (?2 between ar.dateFrom and ar.dateEnd)" +
            "or (?1< ar.dateFrom and ?2>ar.dateEnd)) and(ar.reservationType = 'ACTIVE' or ar.reservationType='DISCOUNTOFFER')))" +
            "and a.price<=?3 and a.avgRating>=?4 and a.noOfPersons>=?5 and a.misc like %?6% and a.misc like %?7% and a.misc like %?8% ")
    Page<Adventure> findAdventuresNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Float maxPrice, Float minRating,
                                              Integer noOfPersons, String tag1, String tag2, String tag3,  Pageable pageable);
}
