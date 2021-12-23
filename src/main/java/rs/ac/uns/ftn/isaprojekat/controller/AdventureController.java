package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.AdventureService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping({"/adventures"})
@Controller
public class AdventureController {

    private final AdventureService adventureService;

    public AdventureController(AdventureService adventureService) {
        this.adventureService = adventureService;
    }

    @RequestMapping({""})
    public String listAdventures(Model model){

        model.addAttribute("adventures", adventureService.findAll());
        return "adventures";
    }


    @RequestMapping(value = "/{id}", method = GET)
    public String printId(Model model, @PathVariable("id") long id) {

        model.addAttribute("adventure", adventureService.findById(id));

        return "adventure";
    }

}
