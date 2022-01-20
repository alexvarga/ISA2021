package rs.ac.uns.ftn.isaprojekat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouseReservation;

import java.time.LocalDateTime;
import java.util.Set;

@Repository
public interface VacationHouseReservationRepository extends PagingAndSortingRepository<VacationHouseReservation, Long> {
    Page<VacationHouseReservation> getAllByUser(User user, Pageable pageable);

    Page<VacationHouseReservation> getAllByUserAndDateEndBefore(User user, LocalDateTime time, Pageable pageable);

    Page<VacationHouseReservation> getAllByUserAndDateEndAfter(User user, LocalDateTime time, Pageable pageable);

    Set<VacationHouseReservation> getAllByVacationHouse_Id(Long vacationHouse_id);

    //current
    @Query(value="select vhr from VacationHouseReservation vhr where vhr.user = ?1 and vhr.dateEnd > ?2 and not(vhr.reservationType ='DISCOUNTOFFER' or vhr.reservationType='CANCELLED') ")
    Page<VacationHouseReservation> getAllByUserAndDateEndAfterAndReservationTypeNotDiscount(User user, LocalDateTime time, Pageable pageable);

    //history or cancelled
    @Query(value="select vhr from VacationHouseReservation vhr where (vhr.user = ?1 and vhr.dateEnd < ?2) and (not vhr.reservationType ='DISCOUNTOFFER') or(vhr.reservationType='CANCELLED') ")
    Page<VacationHouseReservation> getAllByUserAndDateEndBeforeAndReservationTypeNotDiscount(User user, LocalDateTime time, Pageable pageable);

    Page<VacationHouseReservation> getAllByReservationType(ReservationType type, Pageable pageable);


    //user has a reservation in that period
    @Query("select case when count(vhr.user.id)>0 then true else false end from VacationHouseReservation vhr where ((?2 between vhr.dateFrom and vhr.dateEnd) or (?3 between vhr.dateFrom and vhr.dateEnd) or (?2<vhr.dateFrom and ?3>vhr.dateEnd) ) and ?1 = vhr.user and ?4=vhr.vacationHouse.id")
    boolean existsByUser(User user, LocalDateTime start, LocalDateTime end, Long id);

    Set<VacationHouseReservation> getAllByUser(User user);

}
