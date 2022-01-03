
package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("")
public class SearchBoatsController {

    private final BoatService boatService;

    public SearchBoatsController(BoatService boatService) {
        this.boatService = boatService;
    }

    @PostMapping("/search/boats")
    String listBoatResults(Model model, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd){




        return listBoatResultsByPage(model,1, "id", "asc", dateFrom, dateEnd);
    }

    @GetMapping("/search/boats/page/{pageNumber}")
    String listBoatResultsByPage(Model model, @PathVariable("pageNumber") int currentPage,
                                 @Param(value="sortField") String sortField,
                                 @Param(value="sortDirection") String sortDirection,
                                 @Param(value="dateFrom") String dateFrom,
                                 @Param(value="dateEnd") String dateEnd){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}


        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateEnd", dateEnd);
        System.out.println(dateFrom + "+++" + dateEnd);


        Page<Boat> page = boatService.findBoatsNotReserved(currentPage, sortField, sortDirection, LocalDateTime.parse(dateFrom+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), LocalDateTime.parse(dateEnd+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        List<Boat> listBoats = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("search", true);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("boats", listBoats);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);



        return "boats";
    }
}
