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
            "(((?1 between br.dateFrom and br.dateEnd) or (?2 between br.dateFrom and br.dateEnd) " +
            "or (?1< br.dateFrom and ?2>br.dateEnd)) and(br.reservationType = 'ACTIVE' or br.reservationType='DISCOUNTOFFER')))" +
            " and b.price<=?3 and b.avgRating>=?4 and b.noOfPersons>=?5 and b.misc like %?6% and b.misc like %?7% and b.misc like %?8% ")
    Page<Boat> findBoatsNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Float maxPrice, Float minRating,
                                    Integer noOfPersons, String tag1, String tag2, String tag3,  Pageable pageable);


    @Query(value="select b from Boat b left join BoatReservation br on b.id=br.boat.id where((?1 between br.dateFrom and br.dateEnd) " +
            "or (?2 between br.dateFrom and br.dateEnd)) and (br.reservationType='DISCOUNTOFFER')")
    Page<Boat> findBoatsDiscountAvailable(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);



}
