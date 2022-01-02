package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping({"/houses"})
public class VacationHouseController {

    private final VacationHouseService vacationHouseService;


    public VacationHouseController(VacationHouseService vacationHouseService) {
        this.vacationHouseService = vacationHouseService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String listHouses(Model model){
        return listVacationHousesByPage(model, 1, "name", "asc");
    }

    @GetMapping({"/page/{pageNumber}"})
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

    @RequestMapping(value = "/{id}", method = GET)
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

}
