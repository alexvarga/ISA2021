package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.Boat;

import java.time.LocalDateTime;

public interface BoatService extends PageableCrudService<Boat, Long> {

    Page<Boat> findBoatsNotReserved(int pageNumber, String sortField, String sortDirection, LocalDateTime dateFrom, LocalDateTime dateEnd);
}
