package rs.ac.uns.ftn.isaprojekat.controller;

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
        System.out.println("hello from vacation houses controller");
        return "houses";
    }

    @RequestMapping(value = "/{id}", method = GET)
    public String printId(Model model, @PathVariable("id") long id) {
        System.out.println(id);

        model.addAttribute("house", vacationHouseService.findById(id));

        return "house";
    }

}
