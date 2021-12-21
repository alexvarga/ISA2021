package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

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
        model.addAttribute("houses", vacationHouseService.findAll());
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
