package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/reserve")
@Controller
public class ReservationProcessController {
    private final BoatReservationService boatReservationService;
    private final BoatService boatService;
    private final UserService userService;
    private final AdventureService adventureService;
    private final AdventureReservationService adventureReservationService;
    private final VacationHouseService vacationHouseService;
    private final VacationHouseReservationService vacationHouseReservationService;


    public ReservationProcessController(BoatReservationService boatReservationService, BoatService boatService, UserService userService, AdventureService adventureService, AdventureReservationService adventureReservationService, VacationHouseService vacationHouseService, VacationHouseReservationService vacationHouseReservationService) {
        this.boatReservationService = boatReservationService;
        this.boatService = boatService;
        this.userService = userService;
        this.adventureService = adventureService;
        this.adventureReservationService = adventureReservationService;
        this.vacationHouseService = vacationHouseService;
        this.vacationHouseReservationService = vacationHouseReservationService;
    }


    @PostMapping({"/", ""})
    String test(@Param(value="entityId") Long entityId, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd) throws UnsupportedEncodingException, MessagingException {

        System.out.println(entityId+ " boat id "+dateFrom+" date from");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Boat boat = boatService.findById(entityId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        dateFrom+=" 00:00";
        dateEnd+=" 00:00";
        LocalDateTime dFrom = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime dEnd = LocalDateTime.parse(dateEnd, formatter);
        //DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory()
        boolean hi = boatReservationService.existsByUser(user, dFrom, dEnd, entityId);
        if (hi){
            System.out.println("nemate praveo rezervacije u ovom terminu");
            return "fail";
        }else {

            Duration diff = Duration.between(dFrom, dEnd);
            Long days = diff.toDays();

            System.out.println(boat.getName() + " " + dateFrom + " " + dateEnd);

            BoatReservation reservation = new BoatReservation();
            reservation.setUser(user);
            reservation.setBoat(boat);
            reservation.setDateFrom(LocalDateTime.parse(dateFrom, formatter));
            reservation.setDateEnd(LocalDateTime.parse(dateEnd, formatter));
            reservation.setPrice(boat.getPrice()*days);
            reservation.setReservationType(ReservationType.ACTIVE);
            reservation.setReservationTime(LocalDateTime.now());
            System.out.println(reservation.getReservationTime());

            boatReservationService.save(1L, reservation);
            userService.sendReservationConfirmationEmail(boat.getName(), "brod", dateFrom, dateEnd, boat.getAddress(), email);

            return "reservation_form";
        }
    }


    @PostMapping({"/adventure", "/adventure/"})
    String adventureReserve(@Param(value="entityId") Long entityId, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd) throws UnsupportedEncodingException, MessagingException {
        System.out.println(entityId+"  adventure id"+dateFrom);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
       Adventure adventure = adventureService.findById(entityId);

        dateFrom+=" 00:00";
        dateEnd+=" 00:00";

        LocalDateTime dFrom = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime dEnd = LocalDateTime.parse(dateEnd, formatter);
        boolean permission = adventureReservationService.existsByUser(user, dFrom, dEnd, entityId);
        if (permission){
            return "fail";
        }else {

            Duration diff = Duration.between(dFrom, dEnd);
            Long days = diff.toDays();

            System.out.println(adventure.getName() + " " + dateFrom + " " + dateEnd);

            AdventureReservation reservation = new AdventureReservation();
            reservation.setUser(user);
            reservation.setAdventure(adventure);
            reservation.setDateFrom(dFrom);
            reservation.setDateEnd(dEnd);
            reservation.setPrice(adventure.getPrice()*days);
            reservation.setReservationType(ReservationType.ACTIVE);
            reservation.setReservationTime(LocalDateTime.now());
            System.out.println(reservation.getReservationTime());

            adventureReservationService.save(1L, reservation);
            userService.sendReservationConfirmationEmail(adventure.getName(), "avanturu", dateFrom, dateEnd, adventure.getAddress(), email);


            return "reservation_form";
        }
    }

    @PostMapping({"/house", "/house/"})
    String houseReserve(@Param(value="entityId") Long entityId, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd) throws UnsupportedEncodingException, MessagingException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        VacationHouse vacationHouse = vacationHouseService.findById(entityId);

        dateFrom+=" 00:00";
        dateEnd+=" 00:00";

        LocalDateTime dFrom = LocalDateTime.parse(dateFrom, formatter);
        LocalDateTime dEnd = LocalDateTime.parse(dateEnd, formatter);
        boolean permission = vacationHouseReservationService.existsByUser(user, dFrom, dEnd, entityId);
        if (permission){
            return "fail";
        }else {

            Duration diff = Duration.between(dFrom, dEnd);
            Long days = diff.toDays();
            System.out.println(days+ " days");

            System.out.println(vacationHouse.getName() + " " + dateFrom + " " + dateEnd);

            VacationHouseReservation reservation = new VacationHouseReservation();
            reservation.setUser(user);
            reservation.setVacationHouse(vacationHouse);
            reservation.setDateFrom(dFrom);
            reservation.setDateEnd(dEnd);
            reservation.setPrice(vacationHouse.getPrice()*days); //calculating price for no of days
            reservation.setReservationType(ReservationType.ACTIVE);
            reservation.setReservationTime(LocalDateTime.now());
            System.out.println(reservation.getReservationTime());

            vacationHouseReservationService.save(1L, reservation);
            userService.sendReservationConfirmationEmail(vacationHouse.getName(), "vikendicu", dateFrom, dateEnd, vacationHouse.getAddress(), email);

            return "reservation_form";
        }
    }



}
