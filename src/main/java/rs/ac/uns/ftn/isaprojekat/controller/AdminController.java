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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    private final IncomeRateService incomeRateService;

    public AdminController(InstructorService instructorService, AdventureService adventureService, AdventureReservationService adventureReservationService, BoatOwnerService boatOwnerService, BoatService boatService, BoatReservationService boatReservationService, VacationHouseOwnerService vacationHouseOwnerService, VacationHouseService vacationHouseService, VacationHouseReservationService vacationHouseReservationService, UserService userService, IncomeRateService incomeRateService) {
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
        this.incomeRateService = incomeRateService;
    }


    @GetMapping("/admin/home")
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
    String listAdventures(Model model) {
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
    String listHouses(Model model) {
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
    String listBoats(Model model) {
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
            System.out.println(reservation.getDateEnd());
            reservation.setBoat(null);
            System.out.println(reservation);
            boatReservationService.save(1L, reservation);
        }
        boatService.deleteById(boat.getId());


        return listBoats(model);


    }

    @GetMapping("/change_pass")
    String showAdminChangePass(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        model.addAttribute("user", user);

        return "admin_change_pass";
    }

    @PostMapping("/change_pass")
    String adminChangePass(User user, BindingResult bindingResult, Model model,
                           @RequestParam(value = "password-confirm", required = false) String passwordConfirm,
                           @RequestParam(value = "new-password", required = false) String passwordNew,
                           Principal principal, HttpSession session, HttpServletResponse response) throws IOException {

        User dbUser = userService.findByEmail(principal.getName());
        System.out.println(dbUser);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!passwordNew.equals(passwordConfirm)) {
            bindingResult.addError(new FieldError("user", "password", "Lozinke se ne poklapaju."));
        }

        if (bindingResult.hasErrors()) {
            System.out.println("imam greske ovde ");
            return "admin_change_pass";
        } else {
            dbUser.setPassword(encoder.encode(passwordNew));
            dbUser.setUserRole(UserRole.ADMIN);

            userService.save(1L, dbUser);
        }

        session.invalidate();

        response.sendRedirect("/login");


        return "adminPage";

    }


    @GetMapping({"/admin/new_admin"})
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "new_admin";
    }


    @PostMapping("/admin/new_admin")
    public String process(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model, HttpServletRequest request,
                          @RequestParam(value = "password-confirm", required = false) String passwordConfirm) {

        if (!user.getPassword().equals(passwordConfirm)) {
            bindingResult.addError(new FieldError("user", "password", "Lozinke se ne poklapaju."));
        }

        if (bindingResult.hasErrors()) {


            return ("new_admin");
        } else {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String rawPass = user.getPassword();
            user.setPassword(encoder.encode(rawPass));


            user.setUserRole(UserRole.ADMIN_NEW);
            user.setEnabled(true);
            user.setLocked(false);


            userService.save(1L, user); //id here does nothing todo


            return "adminPage";
        }

    }



    @PostMapping("/admin/reports")
    public String customReport(Model model, @Param(value = "start") String start, @Param(value = "end") String end) {

        Float boatPercent = (incomeRateService.findByEntityName("boat").getEntityPercent())/100;
        Float adventurePercent = (incomeRateService.findByEntityName("adventure").getEntityPercent())/100;
        Float housePercent = (incomeRateService.findByEntityName("house").getEntityPercent())/100;

        LocalDateTime startDateTime = LocalDateTime.parse(start + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        LocalDateTime endDateTime = LocalDateTime.parse(end + " 00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        System.out.println("start " + startDateTime + " end " + endDateTime);

        Map<String, Float> boats = new LinkedHashMap<String, Float>();
        Map<String, Float> adventures = new LinkedHashMap<String, Float>();
        Map<String, Float> houses = new LinkedHashMap<String, Float>();

        ArrayList<BoatReservation> boatRes = boatReservationService.findAll();
        for (BoatReservation br : boatRes) {
            if (br.getDateFrom().isBefore(endDateTime) && br.getDateFrom().isAfter(startDateTime) && br.getReservationType().equals(ReservationType.ACTIVE)) {

                String dateString = br.getDateFrom().toLocalDate().toString();
                if (adventures.get(dateString) == null) {
                    adventures.put(dateString, 0F);
                }
                if (houses.get(dateString) == null) {
                    houses.put(dateString, 0F);
                }

                Float temp = boats.get(dateString);
                if (temp == null) {
                    boats.put(dateString, br.getPrice()*boatPercent);
                } else {
                    boats.put(dateString, temp + br.getPrice()*boatPercent);
                }
            }
        }

        ArrayList<AdventureReservation> adventureRes = adventureReservationService.findAll();
        for (AdventureReservation ar : adventureRes) {
            if (ar.getDateFrom().isBefore(endDateTime) && ar.getDateFrom().isAfter(startDateTime) && ar.getReservationType().equals(ReservationType.ACTIVE)) {

                String dateString = ar.getDateFrom().toLocalDate().toString();
                if (boats.get(dateString) == null) {
                    boats.put(dateString, 0F);
                }
                if (houses.get(dateString) == null) {
                    houses.put(dateString, 0F);
                }

                Float temp = adventures.get(dateString);
                if (temp == null) {
                    adventures.put(dateString, ar.getPrice()*adventurePercent);
                } else {
                    adventures.put(dateString, temp + ar.getPrice()*adventurePercent);
                }
            }
        }

        ArrayList<VacationHouseReservation> houseRes = vacationHouseReservationService.findAll();
        for (VacationHouseReservation vhr : houseRes) {
            if (vhr.getDateFrom().isBefore(endDateTime) && vhr.getDateFrom().isAfter(startDateTime) && vhr.getReservationType().equals(ReservationType.ACTIVE)) {

                String dateString = vhr.getDateFrom().toLocalDate().toString();
                if (boats.get(dateString) == null) {
                    boats.put(dateString, 0F);
                }
                if (adventures.get(dateString) == null) {
                    adventures.put(dateString, 0F);
                }

                Float temp = houses.get(dateString);
                if (temp == null) {
                    houses.put(dateString, vhr.getPrice()*housePercent);
                } else {
                    houses.put(dateString, temp + vhr.getPrice()*housePercent);
                }
            }
        }

        ArrayList<Float> list = new ArrayList<Float>();
        list.addAll(boats.values());
        list.addAll(adventures.values());
        list.addAll(houses.values());

        if(!list.isEmpty()){
            model.addAttribute("max", Collections.max(list));
        }

        model.addAttribute("boatKeySet", boats.keySet());
        model.addAttribute("boatValues", boats.values());
        model.addAttribute("adventureValues", adventures.values());
        model.addAttribute("houseValues", houses.values());

        return "reports/report_custom";
    }

    @GetMapping({"/admin/reports", "/admin/reports/"})
    public String showReportYear(Model model) {

        Float boatPercent = (incomeRateService.findByEntityName("boat").getEntityPercent())/100;
        Float adventurePercent = (incomeRateService.findByEntityName("adventure").getEntityPercent())/100;
        Float housePercent = (incomeRateService.findByEntityName("house").getEntityPercent())/100;

        Map<String, Float> boats = new LinkedHashMap<String, Float>();
        Map<String, Float> adventures = new LinkedHashMap<String, Float>();
        Map<String, Float> houses = new LinkedHashMap<String, Float>();

        Map<String, Float> boatsMonth = new LinkedHashMap<String, Float>();
        Map<String, Float> adventuresMonth = new LinkedHashMap<String, Float>();
        Map<String, Float> housesMonth = new LinkedHashMap<String, Float>();

        Map<String, Float> boatsWeek = new LinkedHashMap<String, Float>();
        Map<String, Float> adventuresWeek = new LinkedHashMap<String, Float>();
        Map<String, Float> housesWeek = new LinkedHashMap<String, Float>();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastDayOfLastMonth = now.minusDays(now.getDayOfMonth());
        LocalDateTime firstDayOfLastMonth = lastDayOfLastMonth.minusMonths(1); //this is actually last day of the month before
        LocalDateTime fistDayLastWeek = now.minusDays(7);

        YearMonth ym = YearMonth.now().minusMonths(12);


        for (int i = 0; i < 12; i++) {
            boats.put(ym.plusMonths(i).toString(), 0F);
            adventures.put(ym.plusMonths(i).toString(), 0F);
            houses.put(ym.plusMonths(i).toString(), 0F);
        }

        for (int i = 1; i < 6; i++) {
            boatsMonth.put((i + ". nedelja"), 0F);
            adventuresMonth.put((i + ". nedelja"), 0F);
            housesMonth.put((i + ". nedelja"), 0F);
        }


        for (int i = 7; i > 0; i--) {

            boatsWeek.put((now.minusDays(i).toLocalDate().toString()), 0F);
            adventuresWeek.put((now.minusDays(i).toLocalDate().toString()), 0F);
            housesWeek.put((now.minusDays(i).toLocalDate().toString()), 0F);
        }

        System.out.println(boatsWeek.keySet());


        ArrayList<BoatReservation> boatRes = boatReservationService.findAll();
        for (BoatReservation br : boatRes) {

            if (br.getDateFrom().isAfter(fistDayLastWeek) && br.getDateFrom().isBefore(now.toLocalDate().atStartOfDay())) {
                boatsWeek.put(br.getDateFrom().toLocalDate().toString(), br.getPrice()*boatPercent);
            }

            if (br.getDateFrom().isBefore(lastDayOfLastMonth) && br.getDateFrom().isAfter(lastDayOfLastMonth.minusMonths(12)) && br.getReservationType().equals(ReservationType.ACTIVE)) {

                Float temp = boats.get(YearMonth.from(br.getDateFrom()).toString());
                boats.put(YearMonth.from(br.getDateFrom()).toString(), temp + br.getPrice()*boatPercent);


                if (br.getDateFrom().isAfter(firstDayOfLastMonth) && br.getDateFrom().isBefore(lastDayOfLastMonth)) {
                    if (br.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(1))) {

                        Float t = boatsMonth.get("1 nedelja");

                        if (t != null) {
                            boatsMonth.put("1. nedelja", t + br.getPrice()*boatPercent);
                        } else {
                            boatsMonth.put("1. nedelja", br.getPrice()*boatPercent);
                        }
                    } else if (br.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(1)) && br.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(2))) {
                        Float t = boatsMonth.get("2. nedelja");
                        if (t != null) {
                            boatsMonth.put("2. nedelja", t + br.getPrice()*boatPercent);
                        } else {
                            boatsMonth.put("2. nedelja", br.getPrice()*boatPercent);
                        }

                    } else if (br.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(2)) && br.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(3))) {
                        Float t = boatsMonth.get("3. nedelja");
                        if (t != null) {
                            boatsMonth.put("3. nedelja", t + br.getPrice()*boatPercent);
                        } else {
                            boatsMonth.put("3. nedelja", br.getPrice()*boatPercent);
                        }

                    } else if (br.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(3)) && br.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(4))) {
                        Float t = boatsMonth.get("4. nedelja");
                        if (t != null) {
                            boatsMonth.put("4. nedelja", t + br.getPrice()*boatPercent);
                        } else {
                            boatsMonth.put("4. nedelja", br.getPrice()*boatPercent);
                        }

                    } else if (br.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(4)) && br.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(5))) {
                        Float t = boatsMonth.get("5. nedelja");
                        if (t != null) {
                            boatsMonth.put("5. nedelja", t + br.getPrice()*boatPercent);
                        } else {
                            boatsMonth.put("5. nedelja", br.getPrice()*boatPercent);
                        }

                    }
                }

            }
        }

        ArrayList<AdventureReservation> adventureRes = adventureReservationService.findAll();
        for (AdventureReservation ar : adventureRes) {

            if (ar.getDateFrom().isAfter(fistDayLastWeek) && ar.getDateFrom().isBefore(now.toLocalDate().atStartOfDay())) {
                adventuresWeek.put(ar.getDateFrom().toLocalDate().toString(), ar.getPrice()*adventurePercent);
            }

            if (ar.getDateFrom().isBefore(lastDayOfLastMonth) && ar.getDateFrom().isAfter(lastDayOfLastMonth.minusMonths(12)) && ar.getReservationType().equals(ReservationType.ACTIVE)) {

                System.out.println(ar.getId());
                Float temp = adventures.get(YearMonth.from(ar.getDateFrom()).toString());
                System.out.println(adventures.get(YearMonth.from(ar.getDateFrom()).toString()));
                adventures.put(YearMonth.from(ar.getDateFrom()).toString(), temp + ar.getPrice()*adventurePercent);

                if (ar.getDateFrom().isAfter(firstDayOfLastMonth) && ar.getDateFrom().isBefore(lastDayOfLastMonth)) {
                    if (ar.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(1))) {

                        Float t = adventuresMonth.get("1 nedelja");

                        if (t != null) {
                            adventuresMonth.put("1. nedelja", t + ar.getPrice()*adventurePercent);
                        } else {
                            adventuresMonth.put("1. nedelja", ar.getPrice()*adventurePercent);
                        }
                    } else if (ar.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(1)) && ar.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(2))) {
                        Float t = adventuresMonth.get("2. nedelja");
                        if (t != null) {
                            adventuresMonth.put("2. nedelja", t + ar.getPrice()*adventurePercent);
                        } else {
                            adventuresMonth.put("2. nedelja", ar.getPrice()*adventurePercent);
                        }

                    } else if (ar.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(2)) && ar.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(3))) {
                        Float t = adventuresMonth.get("3. nedelja");
                        if (t != null) {
                            adventuresMonth.put("3. nedelja", t + ar.getPrice()*adventurePercent);
                        } else {
                            adventuresMonth.put("3. nedelja", ar.getPrice()*adventurePercent);
                        }

                    } else if (ar.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(3)) && ar.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(4))) {
                        Float t = adventuresMonth.get("4. nedelja");
                        if (t != null) {
                            adventuresMonth.put("4. nedelja", t + ar.getPrice()*adventurePercent);
                        } else {
                            adventuresMonth.put("4. nedelja", ar.getPrice()*adventurePercent);
                        }

                    } else if (ar.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(4)) && ar.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(5))) {
                        Float t = adventuresMonth.get("5. nedelja");
                        if (t != null) {
                            adventuresMonth.put("5. nedelja", t + ar.getPrice()*adventurePercent);
                        } else {
                            adventuresMonth.put("5. nedelja", ar.getPrice()*adventurePercent);
                        }

                    }
                }

            }
        }

        ArrayList<VacationHouseReservation> houseRes = vacationHouseReservationService.findAll();
        for (VacationHouseReservation vhr : houseRes) {

            if (vhr.getDateFrom().isAfter(fistDayLastWeek) && vhr.getDateFrom().isBefore(now.toLocalDate().atStartOfDay())) {
                housesWeek.put(vhr.getDateFrom().toLocalDate().toString(), vhr.getPrice()*housePercent);
            }

            if (vhr.getDateFrom().isBefore(lastDayOfLastMonth) && vhr.getDateFrom().isAfter(lastDayOfLastMonth.minusMonths(12)) && vhr.getReservationType().equals(ReservationType.ACTIVE)) {

                System.out.println(vhr.getId());
                Float temp = houses.get(YearMonth.from(vhr.getDateFrom()).toString());
                System.out.println(houses.get(YearMonth.from(vhr.getDateFrom()).toString()));
                houses.put(YearMonth.from(vhr.getDateFrom()).toString(), temp + vhr.getPrice()*housePercent);

                if (vhr.getDateFrom().isAfter(firstDayOfLastMonth) && vhr.getDateFrom().isBefore(lastDayOfLastMonth)) {
                    if (vhr.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(1))) {

                        Float t = housesMonth.get("1 nedelja");

                        if (t != null) {
                            housesMonth.put("1. nedelja", t + vhr.getPrice()*housePercent);
                        } else {
                            housesMonth.put("1. nedelja", vhr.getPrice()*housePercent);
                        }
                    } else if (vhr.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(1)) && vhr.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(2))) {
                        Float t = housesMonth.get("2. nedelja");
                        if (t != null) {
                            housesMonth.put("2. nedelja", t + vhr.getPrice()*housePercent);
                        } else {
                            housesMonth.put("2. nedelja", vhr.getPrice()*housePercent);
                        }

                    } else if (vhr.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(2)) && vhr.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(3))) {
                        Float t = housesMonth.get("3. nedelja");
                        if (t != null) {
                            housesMonth.put("3. nedelja", t + vhr.getPrice()*housePercent);
                        } else {
                            housesMonth.put("3. nedelja", vhr.getPrice()*housePercent);
                        }

                    } else if (vhr.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(3)) && vhr.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(4))) {
                        Float t = housesMonth.get("4. nedelja");
                        if (t != null) {
                            housesMonth.put("4. nedelja", t + vhr.getPrice()*housePercent);
                        } else {
                            housesMonth.put("4. nedelja", vhr.getPrice()*housePercent);
                        }

                    } else if (vhr.getDateFrom().isAfter(firstDayOfLastMonth.plusWeeks(4)) && vhr.getDateFrom().isBefore(firstDayOfLastMonth.plusWeeks(5))) {
                        Float t = housesMonth.get("5. nedelja");
                        if (t != null) {
                            housesMonth.put("5. nedelja", t + vhr.getPrice()*housePercent);
                        } else {
                            housesMonth.put("5. nedelja", vhr.getPrice()*housePercent);
                        }

                    }
                }

            }
        }


        System.out.println(boatsWeek.keySet() + " boatsweek keyset");

        ArrayList<Float> list = new ArrayList<Float>();
        list.addAll(boats.values());
        list.addAll(adventures.values());
        list.addAll(houses.values());

        model.addAttribute("yearMax", Collections.max(list));

        list.clear();
        list.addAll(boatsMonth.values());
        list.addAll(adventuresMonth.values());
        list.addAll(housesMonth.values());

        model.addAttribute("monthMax", Collections.max(list));

        list.clear();
        list.addAll(boatsWeek.values());
        list.addAll(adventuresWeek.values());
        list.addAll(housesWeek.values());

        model.addAttribute("weekMax", Collections.max(list));


        model.addAttribute("boatKeySet", boats.keySet());
        model.addAttribute("boatValues", boats.values());
        model.addAttribute("adventureValues", adventures.values());
        model.addAttribute("houseValues", houses.values());

        model.addAttribute("boatMonthKeySet", boatsMonth.keySet());
        model.addAttribute("boatsMonth", boatsMonth.values());
        model.addAttribute("adventuresMonth", adventuresMonth.values());
        model.addAttribute("housesMonth", housesMonth.values());

        model.addAttribute("boatWeekKeySet", boatsWeek.keySet());
        model.addAttribute("boatsWeek", boatsWeek.values());
        model.addAttribute("adventuresWeek", adventuresWeek.values());
        model.addAttribute("housesWeek", housesWeek.values());


        return "reports/report_year_month_week";
    }


}
