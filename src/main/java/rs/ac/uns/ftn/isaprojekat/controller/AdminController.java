package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
public class AdminController {

    private final InstructorService instructorService;
    private final AdventureService adventureService;
    private final AdventureReservationService adventureReservationService;

    private final BoatOwnerService boatOwnerService;
    private final BoatService boatService;
    private final BoatReservationService boatReservationService;

    private final VacationHouseOwnerService vacationHouseOwnerService;
    private final VacationHouseService vacationHouseService;
    private final VacationHouseReservationService vacationHouseReservationService;

    private final UserService userService;

    public AdminController(InstructorService instructorService, AdventureService adventureService, AdventureReservationService adventureReservationService, BoatOwnerService boatOwnerService, BoatService boatService, BoatReservationService boatReservationService, VacationHouseOwnerService vacationHouseOwnerService, VacationHouseService vacationHouseService, VacationHouseReservationService vacationHouseReservationService, UserService userService) {
        this.instructorService = instructorService;
        this.adventureService = adventureService;
        this.adventureReservationService = adventureReservationService;
        this.boatOwnerService = boatOwnerService;
        this.boatService = boatService;
        this.boatReservationService = boatReservationService;
        this.vacationHouseOwnerService = vacationHouseOwnerService;
        this.vacationHouseService = vacationHouseService;
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.userService = userService;
    }

    @GetMapping("/adminPage")
    public String adminPage() {

        return "adminPage";
    }

    @GetMapping("/admin/instructors")
    public String listInstructors(Model model) {


        return listInstructorsByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/instructors/page/{pageNumber}")
    public String listInstructorsByPage(Model model,
                                        @PathVariable("pageNumber") int currentPage,
                                        @Param(value = "sortField") String sortField,
                                        @Param(value = "sortDirection") String sortDirection) {

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }
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
    String deleteInstructor(Model model, @Param("id") Long id) {
        System.out.println(id);
        // not a very scalable solution :(
        Instructor instructor = instructorService.findById(id);
        Set<Adventure> adventures = instructor.getAdventures();
        for (Adventure adventure : adventures) {
            Set<AdventureReservation> reservations = adventureReservationService.getAllByAdventure_Id(adventure.getId());

            for (AdventureReservation reservation : reservations) {
                reservation.setAdventure(null);
                adventureReservationService.save(1L, reservation);
            }
            adventureService.deleteById(adventure.getId());
        }
        instructorService.deleteById(id);

        return listBoatOwners(model);
    }


    @GetMapping("/admin/boatOwners")
    public String listBoatOwners(Model model) {


        return listBoatOwnersByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/boatOwners/page/{pageNumber}")
    public String listBoatOwnersByPage(Model model,
                                       @PathVariable("pageNumber") int currentPage,
                                       @Param(value = "sortField") String sortField,
                                       @Param(value = "sortDirection") String sortDirection) {

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }
        Page<BoatOwner> page = boatOwnerService.findAll(currentPage, sortField, sortDirection);
        List<BoatOwner> listBoatOwners = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("boatOwners", listBoatOwners);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "boat_owners";

    }

    @DeleteMapping("/admin/boatOwners/delete")
    String deleteBoatOwners(Model model, @Param("id") Long id) {
        System.out.println(id);

        BoatOwner owner = boatOwnerService.findById(id);
        Set<Boat> boats = owner.getBoats();
        for (Boat boat : boats) {
            Set<BoatReservation> reservations = boatReservationService.getAllByBoat_Id(boat.getId());
            System.out.println(boat.getId());

            for (BoatReservation reservation : reservations) {
                reservation.setBoat(null);
                System.out.println(reservation);
                boatReservationService.save(1L, reservation);
            }
            boatService.deleteById(boat.getId());
        }
        boatOwnerService.deleteById(id);

        return listBoatOwners(model);
    }


    @GetMapping("/admin/houseOwners")
    public String listVacationHouseOwners(Model model) {


        return listVacationHouseOwnersByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/houseOwners/page/{pageNumber}")
    public String listVacationHouseOwnersByPage(Model model,
                                                @PathVariable("pageNumber") int currentPage,
                                                @Param(value = "sortField") String sortField,
                                                @Param(value = "sortDirection") String sortDirection) {

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }
        Page<VacationHouseOwner> page = vacationHouseOwnerService.findAll(currentPage, sortField, sortDirection);
        List<VacationHouseOwner> listVacationHouseOwners = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("houseOwners", listVacationHouseOwners);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);

        return "vacation_house_owners";

    }

    @DeleteMapping("/admin/houseOwners/delete")
    String deleteVacationHouseOwner(Model model, @Param("id") Long id) {
        System.out.println(id);

        VacationHouseOwner owner = vacationHouseOwnerService.findById(id);
        Set<VacationHouse> houses = owner.getVacationHouses();
        for (VacationHouse house : houses) {
            Set<VacationHouseReservation> reservations =
                    vacationHouseReservationService.getAllByVacationHouse_Id(house.getId());
            System.out.println(house.getId());

            for (VacationHouseReservation reservation : reservations) {
                reservation.setVacationHouse(null);
                System.out.println(reservation);
                vacationHouseReservationService.save(1L, reservation);
            }
            vacationHouseService.deleteById(house.getId());
        }
        vacationHouseOwnerService.deleteById(id);

        return listVacationHouseOwners(model);


    }

    @GetMapping("/admin/adventures")
    String listAdventures(Model model){
        return listAdventuresByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/adventures/page/{pageNumber}")
    String listAdventuresByPage(Model model,
                                @PathVariable("pageNumber") int currentPage,
                                @Param(value = "sortField") String sortField,
                                @Param(value = "sortDirection") String sortDirection) {

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }

        Page<Adventure> page = adventureService.findAll(currentPage, sortField, sortDirection);
        List<Adventure> listAdventures = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("adventures", listAdventures);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);


        return "admin_adventures";

    }

    @DeleteMapping("/admin/adventures/delete")
    String deleteAdventure(Model model, @Param("id") Long id) {
        System.out.println(id);

        Adventure adventure = adventureService.findById(id);
            Set<AdventureReservation> reservations =
                    adventureReservationService.getAllByAdventure_Id(adventure.getId());

            for (AdventureReservation reservation : reservations) {
                reservation.setAdventure(null);
                System.out.println(reservation);
                adventureReservationService.save(1L, reservation);
            }
            adventureService.deleteById(adventure.getId());


        return listAdventures(model);


    }




    @GetMapping("/admin/houses")
    String listHouses(Model model){
        return listHousesByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/houses/page/{pageNumber}")
    String listHousesByPage(Model model,
                                @PathVariable("pageNumber") int currentPage,
                                @Param(value = "sortField") String sortField,
                                @Param(value = "sortDirection") String sortDirection) {

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }

        Page<VacationHouse> page = vacationHouseService.findAll(currentPage, sortField, sortDirection);
        List<VacationHouse> listHouses = page.getContent();

        Long numberOfElements = page.getTotalElements();
        int numberOfPages = page.getTotalPages();
        System.out.println(numberOfPages);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("numberOfElements", numberOfElements);
        model.addAttribute("numberOfPages", numberOfPages);
        model.addAttribute("houses", listHouses);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);

        String reverseSortDirection = sortDirection.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverseSortDirection", reverseSortDirection);


        return "admin_houses";

    }

    @DeleteMapping("/admin/houses/delete")
    String deleteVacationHouse(Model model, @Param("id") Long id) {


        VacationHouse vacationHouse = vacationHouseService.findById(id);
        Set<VacationHouseReservation> reservations =
                vacationHouseReservationService.getAllByVacationHouse_Id(vacationHouse.getId());

        for (VacationHouseReservation reservation : reservations) {
            reservation.setVacationHouse(null);
            System.out.println(reservation);
            vacationHouseReservationService.save(1L, reservation);
        }
        vacationHouseService.deleteById(vacationHouse.getId());


        return listHouses(model);


    }


    @GetMapping("/admin/boats")
    String listBoats(Model model){
        return listBoatsByPage(model, 1, "id", "asc");
    }

    @GetMapping("/admin/boats/page/{pageNumber}")
    String listBoatsByPage(Model model,
                                @PathVariable("pageNumber") int currentPage,
                                @Param(value = "sortField") String sortField,
                                @Param(value = "sortDirection") String sortDirection) {

        if (sortField == null) {
            sortField = "id";
        }
        if (sortDirection == null) {
            sortDirection = "asc";
        }

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


        return "admin_boats";

    }

    @DeleteMapping("/admin/boats/delete")
    String deleteBoat(Model model, @Param("id") Long id) {


        Boat boat = boatService.findById(id);
        Set<BoatReservation> reservations =
                boatReservationService.getAllByBoat_Id(boat.getId());

        for (BoatReservation reservation : reservations) {
            reservation.setBoat(null);
            System.out.println(reservation);
            boatReservationService.save(1L, reservation);
        }
        boatService.deleteById(boat.getId());


        return listBoats(model);


    }
    @GetMapping("/change_pass")
    String showAdminChangePass(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);

        return "admin_change_pass";
    }
    @PostMapping("/change_pass")
    String adminChangePass(User user, BindingResult bindingResult, Model model,
                           @RequestParam(value="password-confirm", required = false) String passwordConfirm,
                           @RequestParam(value="new-password", required = false) String passwordNew,
                           Principal principal, HttpSession session, HttpServletResponse response) throws IOException {

        User dbUser = userService.findByEmail(principal.getName());
        System.out.println(dbUser);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if(!passwordNew.equals(passwordConfirm)){
            bindingResult.addError(new FieldError("user", "password", "Lozinke se ne poklapaju."));
        }

        if(bindingResult.hasErrors()){
            System.out.println("imam greske ovde ");
            return "admin_change_pass";
        }else{
            dbUser.setPassword(encoder.encode(passwordNew));
            dbUser.setUserRole(UserRole.ADMIN);

            userService.save(1L, dbUser);
        }

        session.invalidate();

        response.sendRedirect("/login");


        return "adminPage";

    }
}
