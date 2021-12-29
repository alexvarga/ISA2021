package rs.ac.uns.ftn.isaprojekat.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final VacationHouseService vacationHouseService;
    private final BoatService boatService;
    private final AdventureService adventureService;
    private final InstructorService instructorService;
    private final VacationHouseOwnerService vacationHouseOwnerService;
    private final BoatOwnerService boatOwnerService;
    private final UserService userService;
    private final BoatReservationService boatReservationService;
    private final VacationHouseReservationService vacationHouseReservationService;
    private final AdventureReservationService adventureReservationService;


    @Autowired
    public DataLoader(VacationHouseService vacationHouseService, BoatService boatService, AdventureService adventureService, InstructorService instructorService, VacationHouseOwnerService vacationHouseOwnerService, BoatOwnerService boatOwnerService, UserService userService, BoatReservationService boatReservationService, VacationHouseReservationService vacationHouseReservationService, AdventureReservationService adventureReservationService) {
        this.vacationHouseService = vacationHouseService;
        this.boatService = boatService;
        this.adventureService = adventureService;
        this.instructorService = instructorService;
        this.vacationHouseOwnerService = vacationHouseOwnerService;
        this.boatOwnerService = boatOwnerService;
        this.userService = userService;
        this.boatReservationService = boatReservationService;
        this.vacationHouseReservationService = vacationHouseReservationService;
        this.adventureReservationService = adventureReservationService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("test");
        user.setFirstName("pera");
        user.setLastName("perić");
        user.setAddress("a street 1");
        user.setCity("a city 1");
        user.setState("a state 1");
        user.setPhoneNumber("000999000");
        user.setPassword("$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum");
        user.setEnabled(true);
        user.setLocked(false);
        user.setUserRole(UserRole.USER);
        userService.save(user.getId(), user);

        User user2 = new User();
        user2.setEmail("user@user");
        //password: test
        user2.setPassword("$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum");
        user2.setEnabled(true);
        user2.setLocked(false);
        user2.setUserRole(UserRole.ADMIN);
        userService.save(user2.getId(), user2);

        User user3 = new User();
        user3.setEmail("disabledUser");
        //password: test
        user3.setPassword("$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum");
        user3.setEnabled(false);
        user3.setLocked(false);
        user3.setUserRole(UserRole.ADMIN);
        userService.save(user3.getId(), user3);

        VacationHouseOwner vho1 = new VacationHouseOwner();
        //vho1.setId(1L);
        vho1.setFirstName("milan");
        vho1.setLastName("milanović");
        vho1.setEmail("aasdf");
        vacationHouseOwnerService.save(vho1.getId(), vho1);


        VacationHouse vh1 = new VacationHouse();
        //vh1.setId(1L);
        vh1.setName("Nova vikendinca test test test");
        vh1.setInfo("info o novoj vikendici 1");
        vh1.setAvgRating(2.5f);
        vh1.setAddress("adresa 1");
        vh1.setVacationHouseOwner(vho1);
        vacationHouseService.save(vh1.getId(), vh1);



        VacationHouse vh2 = new VacationHouse();
        //vh2.setId(2L);
        vh2.setName("Nova vikendinca 2");
        vh2.setInfo("info o novoj vikendici 2");
        vh2.setAvgRating(5f);
        vh2.setAddress("adresa 2");
        vh2.setVacationHouseOwner(vho1);

        vacationHouseService.save(vh2.getId(), vh2);



        VacationHouse vh3 = new VacationHouse();
        //vh3.setId(3L);
        vh3.setName("Nova vikendinca 3");
        vh3.setInfo("info o novoj vikendici 3");
        vh3.setAvgRating(5f);
        vh3.setAddress("adresa 3");

        vacationHouseService.save(vh3.getId(), vh3);


        BoatOwner bo1 = new BoatOwner();
        //bo1.setId(1L);
        bo1.setFirstName("boat");
        bo1.setLastName("owner");
        boatOwnerService.save(bo1.getId(), bo1);


        Boat b1 = new Boat();
        //b1.setId(1l);
        b1.setName("Boat name");
        b1.setInfo("boat 1 info");
        b1.setAvgRating(5f);
        b1.setAddress("adresa");
        b1.setOwner(bo1);
        boatService.save(b1.getId(), b1);

        Boat b2 = new Boat();
        //b1.setId(1l);
        b2.setName("Super Kul Brod");
        b2.setInfo("boat 2 info");
        b2.setAvgRating(5f);
        b2.setAddress("adresa");
        b2.setOwner(bo1);
        boatService.save(b2.getId(), b2);

        Instructor i1 = new Instructor();
        //i1.setId(1L);
        i1.setFirstName("ivan");
        i1.setLastName("ivanovic");
        i1.setEmail("super@cool.com");
        i1.setPassword("plaintext");
        instructorService.save(i1.getId(), i1);

        Adventure a1 = new Adventure();
        //a1.setId(1L);
        a1.setName("Adventure 1 name");
        a1.setInfo("Adventure 1 info");
        a1.setAvgRating(5f);
        a1.setAddress("adresa");

        a1.setInstructor(i1);
        adventureService.save(a1.getId(), a1);




        Adventure a2 = new Adventure();
        //a2.setId(2l);
        a2.setName("Adventure 2 name");
        a2.setInfo("Adventure 2 info");
        a2.setAvgRating(5f);
        a2.setAddress("adresa");

        a2.setInstructor(i1);
        adventureService.save(a2.getId(), a2);

        BoatReservation br1 = new BoatReservation();
        br1.setBoat(b1);
        br1.setUser(user);
        br1.setReservationType(ReservationType.RESERVATION);
        br1.setReservationTime(LocalDateTime.now());
        boatReservationService.save(1L, br1);

        BoatReservation br2 = new BoatReservation();
        br2.setBoat(b2);
        br2.setUser(user);
        br2.setReservationType(ReservationType.RESERVATION);
        br2.setReservationTime(LocalDateTime.now());
        boatReservationService.save(1L, br2);


        VacationHouseReservation vhr1 = new VacationHouseReservation();
        vhr1.setVacationHouse(vh1);
        vhr1.setUser(user);
        vhr1.setReservationType(ReservationType.RESERVATION);
        vhr1.setReservationTime(LocalDateTime.now());
        vacationHouseReservationService.save(1L, vhr1);

        AdventureReservation ar1 = new AdventureReservation();
        ar1.setAdventure(a1);
        ar1.setUser(user);
        ar1.setReservationType(ReservationType.RESERVATION);
        ar1.setReservationTime(LocalDateTime.now());
        adventureReservationService.save(1L, ar1);




    }
}
