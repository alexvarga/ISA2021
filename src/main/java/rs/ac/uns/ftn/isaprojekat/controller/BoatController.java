package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RequestMapping({"/boats"})
@Controller
public class BoatController {

    private final BoatService boatService;

    public BoatController(BoatService boatService) {
        this.boatService = boatService;
    }

    @GetMapping({"/", ""})
    public String listBoats(Model model){
        return listBoatsByPage(model, 1, "name", "asc");
    }

    @GetMapping({"/page/{pageNumber}"})
    public String listBoatsByPage(Model model,
                                  @PathVariable("pageNumber") int currentPage,
                                  @Param(value="sortField") String sortField,
                                  @Param(value="sortDirection") String sortDirection){
       // currentPage = 1;
        System.out.println(sortField);
        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<Boat> page = boatService.findAll(currentPage, sortField, sortDirection);
        List<Boat> listBoats = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("boats", listBoats);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "boats";
    }


    @RequestMapping(value = "/{id}", method = GET)
    public String printId(Model model, @PathVariable("id") long id) {

        model.addAttribute("boat", boatService.findById(id));

        return "boat";
    }


}
