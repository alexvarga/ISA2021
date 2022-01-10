package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
//@RequestMapping({""})
public class VacationHouseController {

    private final VacationHouseService vacationHouseService;


    public VacationHouseController(VacationHouseService vacationHouseService) {
        this.vacationHouseService = vacationHouseService;
    }

    @RequestMapping({"/houses", "/houses/"})
    public String listHouses(Model model){
        return listVacationHousesByPage(model, 1, "name", "asc");
    }

    @GetMapping({"/houses/page/{pageNumber}"})
    public String listVacationHousesByPage(Model model,
                                           @PathVariable("pageNumber") int currentPage,
                                           @Param(value="sortField") String sortField,
                                           @Param(value="sortDirection") String sortDirection){

        System.out.println(sortField);
        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<VacationHouse> page = vacationHouseService.findAll(currentPage, sortField, sortDirection);
        List<VacationHouse> entities = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("houses", entities);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "houses";
    }

    @RequestMapping(value = "/houses/{id}", method = GET)
    public String printId(Model model, @PathVariable("id") long id) {

        if(vacationHouseService.findById(id)!=null) {
            model.addAttribute("house", vacationHouseService.findById(id));
        }else {
            return "houses";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            System.out.println(currentUserName); ;
            model.addAttribute("loggedin", currentUserName);
        }



        return "house";
    }

    @PostMapping("/search/houses")
    String listHouseResults(Model model,                            @ModelAttribute(value = "dateFrom") String dateFrom,
                            @ModelAttribute(value = "dateEnd") String dateEnd,
                            @ModelAttribute(value = "tag1") String tag1,
                            @ModelAttribute(value = "tag2") String tag2,
                            @ModelAttribute(value = "tag3") String tag3,
                            @ModelAttribute(value = "maxPrice") Float maxPrice,
                            @ModelAttribute(value = "minRating") Float minRating,
                            @ModelAttribute(value = "noOfPersons") Integer noOfPersons) {


        return listHouseResultsByPage(model,1, "id", "asc", dateFrom, dateEnd,
                tag1, tag2, tag3, maxPrice, minRating, noOfPersons );
    }

    @GetMapping("/search/houses/page/{pageNumber}")
    String listHouseResultsByPage(Model model, @PathVariable("pageNumber") int currentPage,
                                  @Param(value="sortField") String sortField,
                                  @Param(value="sortDirection") String sortDirection,
                                  @Param(value="dateFrom") String dateFrom,
                                  @Param(value="dateEnd") String dateEnd,
                                  @Param(value = "tag1") String tag1,
                                  @Param(value = "tag2") String tag2,
                                  @Param(value = "tag3") String tag3,
                                  @Param(value = "maxPrice") Float maxPrice,
                                  @Param(value = "minRating") Float minRating,
                                  @Param(value = "noOfPersons") Integer noOfPersons){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}


        model.addAttribute("dateFrom", dateFrom);
        model.addAttribute("dateEnd", dateEnd);
        System.out.println(dateFrom + "+++" + dateEnd);


        Page<VacationHouse> page = vacationHouseService.findVacationHousesNotReserved(currentPage,
                sortField, sortDirection,
                LocalDateTime.parse(dateFrom+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.parse(dateEnd+" 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                maxPrice,
                minRating,
                noOfPersons,
                tag1, tag2, tag3);

        List<VacationHouse> listHouses = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("search", true);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("houses", listHouses);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("minRating", minRating);
        model.addAttribute("noOfPersons", noOfPersons);
        model.addAttribute("tag1", tag1);
        model.addAttribute("tag2", tag2);
        model.addAttribute("tag3", tag3);


        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "houses";
    }

}
