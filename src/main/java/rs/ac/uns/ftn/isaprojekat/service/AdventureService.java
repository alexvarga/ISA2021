package rs.ac.uns.ftn.isaprojekat.service;

import org.springframework.data.domain.Page;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;

import java.time.LocalDateTime;

public interface AdventureService extends PageableCrudService<Adventure, Long> {

    Page<Adventure> findAdventureNotReserved(int pageNumber,
                                             String sortField,
                                             String sortDirection,
                                             LocalDateTime dateFrom,
                                             LocalDateTime dateEnd,
                                             Float maxPrice,
                                             Float minRating,
                                             Integer noOfPersons,
                                             String tag1,
                                             String tag2,
                                             String tag3);

}
