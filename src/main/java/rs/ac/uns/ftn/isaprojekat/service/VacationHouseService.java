package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;

import java.time.LocalDateTime;


public interface VacationHouseService extends PageableCrudService<VacationHouse, Long> {

    Page<VacationHouse> findVacationHousesNotReserved(int pageNumber, String sortField, String sortDirection, LocalDateTime dateFrom, LocalDateTime dateEnd);

}
