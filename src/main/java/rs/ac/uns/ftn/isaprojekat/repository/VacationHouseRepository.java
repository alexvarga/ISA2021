package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;

import java.time.LocalDateTime;

@Repository
public interface VacationHouseRepository extends PagingAndSortingRepository<VacationHouse, Long> {

    @Query(value="select vh from VacationHouse vh where vh.id not in" +
            " (select vha.id from VacationHouse vha left join VacationHouseReservation vhr on vha.id=vhr.vacationHouse.id where " +
            "(((?1 between vhr.dateFrom and vhr.dateEnd) or (?2 between vhr.dateFrom and vhr.dateEnd) " +
            "or (?1< vhr.dateFrom and ?2>vhr.dateEnd)) and(vhr.reservationType = 'ACTIVE' or vhr.reservationType='DISCOUNTOFFER')))" +
            " and vh.price<=?3 and vh.avgRating>=?4 and vh.noOfPersons>=?5 and vh.misc like %?6% and vh.misc like %?7% and vh.misc like %?8% ")
    Page<VacationHouse> findVacationHousesNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd,  Float maxPrice, Float minRating,
                                                      Integer noOfPersons, String tag1, String tag2, String tag3,  Pageable pageable);
}
