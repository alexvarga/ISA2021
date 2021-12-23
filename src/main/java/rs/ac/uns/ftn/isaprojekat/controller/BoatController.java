package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
        return "boats";
    }


    @RequestMapping(value = "/{id}", method = GET)
    public String printId(Model model, @PathVariable("id") long id) {

        model.addAttribute("boat", boatService.findById(id));

        return "boat";
    }


}
