package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

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
    String test(@Param(value="boatId") Long boatId, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Boat boat = boatService.findById(boatId);

        dateFrom+=" 00:00";
        dateEnd+=" 00:00";
        //DateTimeFormatterFactory formatterFactory = new DateTimeFormatterFactory()

        System.out.println(boat.getName()+" "+dateFrom + " "+ dateEnd);

        BoatReservation reservation = new BoatReservation();
        reservation.setUser(user);
        reservation.setBoat(boat);
        reservation.setDateFrom(LocalDateTime.parse(dateFrom, formatter));
        reservation.setDateEnd(LocalDateTime.parse(dateEnd, formatter));
        reservation.setReservationType(ReservationType.ACTIVE);
        reservation.setReservationTime(LocalDateTime.now());
        System.out.println(reservation.getReservationTime());

        boatReservationService.save(1L, reservation);

        return "reservation_form";
    }


    @PostMapping({"/adventure", "/adventure/"})
    String adventureReserve(@Param(value="adventureId") Long adventureId, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        Adventure adventure = adventureService.findById(adventureId);

        dateFrom+=" 00:00";
        dateEnd+=" 00:00";

        System.out.println(adventure.getName()+" "+dateFrom + " "+ dateEnd);

        AdventureReservation reservation = new AdventureReservation();
        reservation.setUser(user);
        reservation.setAdventure(adventure);
        reservation.setDateFrom(LocalDateTime.parse(dateFrom, formatter));
        reservation.setDateEnd(LocalDateTime.parse(dateEnd, formatter));
        reservation.setReservationType(ReservationType.ACTIVE);
        reservation.setReservationTime(LocalDateTime.now());
        System.out.println(reservation.getReservationTime());

        adventureReservationService.save(1L, reservation);

        return "reservation_form";
    }

    @PostMapping({"/house", "/house/"})
    String houseReserve(@Param(value="houseId") Long houseId, @Param(value="dateFrom") String dateFrom, @Param(value = "dateEnd") String dateEnd){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User user = userService.findByEmail(email);
        VacationHouse vacationHouse = vacationHouseService.findById(houseId);

        dateFrom+=" 00:00";
        dateEnd+=" 00:00";

        System.out.println(vacationHouse.getName()+" "+dateFrom + " "+ dateEnd);

        VacationHouseReservation reservation = new VacationHouseReservation();
        reservation.setUser(user);
        reservation.setVacationHouse(vacationHouse);
        reservation.setDateFrom(LocalDateTime.parse(dateFrom, formatter));
        reservation.setDateEnd(LocalDateTime.parse(dateEnd, formatter));
        reservation.setReservationType(ReservationType.ACTIVE);
        reservation.setReservationTime(LocalDateTime.now());
        System.out.println(reservation.getReservationTime());

        vacationHouseReservationService.save(1L, reservation);

        return "reservation_form";
    }


}