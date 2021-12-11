package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

@Controller
public class IndexController {

    private final VacationHouseService vacationHouseService;

    public IndexController(VacationHouseService vacationHouseService) {
        this.vacationHouseService = vacationHouseService;
    }

    @RequestMapping({"", "/", "index", "index.html"})
    public String index(Model model){

        model.addAttribute("houses", vacationHouseService.findAll());

        return "index";
    }
}
