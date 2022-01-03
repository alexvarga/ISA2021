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

    @Query(value="select distinct v from VacationHouse v left join VacationHouseReservation vr on v.id=vr.vacationHouse.id " +
            "where ?1< vr.dateFrom and ?2 < vr.dateFrom " +
            "or ?1> vr.dateEnd and ?2 > vr.dateEnd " +
            "or vr.dateFrom is null")
    Page<VacationHouse> findVacationHousesNotReserved(LocalDateTime dateFrom, LocalDateTime dateEnd, Pageable pageable);
}
