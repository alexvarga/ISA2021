package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

@RequestMapping({"/boats"})
@Controller
public class BoatController {

    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @RequestMapping({""})
    public String listBoats(Model model){
        model.addAttribute("boats", boatService.findAll());
        System.out.println("hello from boats controller");
        return "boats";
    }

}
