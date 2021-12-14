package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

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
}
