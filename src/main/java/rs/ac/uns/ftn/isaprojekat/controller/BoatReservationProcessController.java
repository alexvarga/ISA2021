package rs.ac.uns.ftn.isaprojekat.controller;

import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.BoatReservation;
import rs.ac.uns.ftn.isaprojekat.model.ReservationType;
import rs.ac.uns.ftn.isaprojekat.model.User;
import rs.ac.uns.ftn.isaprojekat.service.BoatReservationService;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;
import rs.ac.uns.ftn.isaprojekat.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequestMapping("/reserve")
@Controller
public class BoatReservationProcessController {
    private final BoatReservationService boatReservationService;
    private final BoatService boatService;
    private final UserService userService;

    public BoatReservationProcessController(BoatReservationService boatReservationService, BoatService boatService, UserService userService) {
        this.boatReservationService = boatReservationService;
        this.boatService = boatService;
        this.userService = userService;
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


}
