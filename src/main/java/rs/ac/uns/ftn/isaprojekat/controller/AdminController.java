package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import rs.ac.uns.ftn.isaprojekat.model.Adventure;
import rs.ac.uns.ftn.isaprojekat.model.AdventureReservation;
import rs.ac.uns.ftn.isaprojekat.model.Instructor;
import rs.ac.uns.ftn.isaprojekat.service.AdventureReservationService;
import rs.ac.uns.ftn.isaprojekat.service.AdventureService;
import rs.ac.uns.ftn.isaprojekat.service.InstructorService;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final InstructorService instructorService;
    private final AdventureService adventureService;
    private final AdventureReservationService adventureReservationService;

    public AdminController(InstructorService instructorService, AdventureService adventureService, AdventureReservationService adventureReservationService) {
        this.instructorService = instructorService;
        this.adventureService = adventureService;
        this.adventureReservationService = adventureReservationService;
    }

    @GetMapping("/adminPage")
    public String adminPage(){

        return "adminPage";
    }

    @GetMapping("/admin/instructors")
    public String listInstructors(Model model){


        return listInstructorsByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/instructors/page/{pageNumber}")
    public String listInstructorsByPage(Model model,
                                        @PathVariable("pageNumber") int currentPage,
                                        @Param(value="sortField") String sortField,
                                        @Param(value="sortDirection") String sortDirection){

        if (sortField==null){sortField="id";}
        if(sortDirection==null){sortDirection="asc";}
        Page<Instructor> page = instructorService.findAll(currentPage, sortField, sortDirection);
        List<Instructor> listInstructors = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("instructors", listInstructors);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "instructors";

    }

    @DeleteMapping("/admin/instructors/delete")
    String deleteInstructor(Model model, @Param("id") Long id){
        System.out.println(id);
       // not a very scalable solution :(
        Instructor instructor = instructorService.findById(id);
        Set<Adventure> adventures = instructor.getAdventures();
        for (Adventure adventure : adventures){
            Set<AdventureReservation> reservations = adventureReservationService.getAllByAdventure_Id(adventure.getId());

            for (AdventureReservation reservation:reservations){
                reservation.setAdventure(null);
                adventureReservationService.save(1L, reservation);
            }
            adventureService.deleteById(adventure.getId());
        }
        instructorService.deleteById(id);

        return listInstructors(model);
    }


}
