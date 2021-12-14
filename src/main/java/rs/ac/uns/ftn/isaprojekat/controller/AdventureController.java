package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.AdventureService;

@RequestMapping({"/adventures"})
@Controller
public class AdventureController {

    private final AdventureService adventureService;

    public AdventureController(AdventureService adventureService) {
        this.adventureService = adventureService;
    }

    @RequestMapping({""})
    String listAdventures(Model model){

        model.addAttribute("adventures", adventureService.findAll());
        System.out.println("hello from adventure controller");
        return "adventures";
    }
}
