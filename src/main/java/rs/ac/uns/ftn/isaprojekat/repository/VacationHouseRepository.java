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
            "(((?1 between vhr.dateFrom and vhr.dateEnd) or (?2 between vhr.dateFrom and vhr.dateEnd) or (?1< vhr.dateFrom and ?2>vhr.dateEnd)) and(vhr.reservationType = 'ACTIVE') ))")
    Page<VacationHouse> findVacationHousesNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);
}
