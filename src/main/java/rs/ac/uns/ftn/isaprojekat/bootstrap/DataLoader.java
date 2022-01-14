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

    private final IncomeRateService incomeRateService;


    @Autowired
    public DataLoader(VacationHouseService vacationHouseService, BoatService boatService, AdventureService adventureService, InstructorService instructorService, VacationHouseOwnerService vacationHouseOwnerService, BoatOwnerService boatOwnerService, UserService userService, BoatReservationService boatReservationService, VacationHouseReservationService vacationHouseReservationService, AdventureReservationService adventureReservationService, IncomeRateService incomeRateService) {
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
        this.incomeRateService = incomeRateService;
    }

    @Override
    public void run(String... args) throws Exception {

        IncomeRate irb = new IncomeRate();
        irb.setEntityName("boat");
        irb.setEntityPercent(20F);
        incomeRateService.save(1L, irb);

        IncomeRate irh = new IncomeRate();
        irh.setEntityName("house");
        irh.setEntityPercent(10F);
        incomeRateService.save(1L, irh);

        IncomeRate ira = new IncomeRate();
        ira.setEntityName("adventure");
        ira.setEntityPercent(5F);
        incomeRateService.save(1L, ira);


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

        User user3 = new User();
        user3.setEmail("disabledUser");
        //password: test
        user3.setPassword("$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum");
        user3.setEnabled(false);
        user3.setLocked(false);
        user3.setUserRole(UserRole.ADMIN);
        userService.save(user3.getId(), user3);

        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum");
        admin.setEnabled(true);
        admin.setLocked(false);
        admin.setUserRole(UserRole.ADMIN);
        userService.save(admin.getId(), admin);

        User admin2 = new User();
        admin2.setEmail("admin2");
        admin2.setPassword("$2y$10$5cnScY3HzyxpCF3CzAoFAeNu2trrzxneiGQH49BQoDQeJZ/zgnHum");
        admin2.setEnabled(true);
        admin2.setLocked(false);
        admin2.setUserRole(UserRole.ADMIN_NEW);
        userService.save(admin2.getId(), admin2);



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
        vh1.setPrice(30F);
        vh1.setNoOfPersons(4);
        vh1.setMisc("");
        vacationHouseService.save(vh1.getId(), vh1);



        VacationHouse vh2 = new VacationHouse();
        //vh2.setId(2L);
        vh2.setName("Nova vikendinca 2");
        vh2.setInfo("info o novoj vikendici 2");
        vh2.setAvgRating(5f);
        vh2.setAddress("adresa 2");
        vh2.setPrice(120F);
        vh2.setNoOfPersons(4);
        vh2.setMisc("");
        vh2.setVacationHouseOwner(vho1);
        vacationHouseService.save(vh2.getId(), vh2);


        VacationHouse vh3 = new VacationHouse();
        //vh3.setId(3L);
        vh3.setName("Nova vikendinca 3");
        vh3.setInfo("info o novoj vikendici 3");
        vh3.setAvgRating(5f);
        vh3.setAddress("adresa 3");
        vh3.setPrice(30F);
        vh3.setNoOfPersons(4);
        vh3.setMisc("");
        vh3.setVacationHouseOwner(vho1);
        vacationHouseService.save(vh3.getId(), vh3);


        BoatOwner bo1 = new BoatOwner();
        //bo1.setId(1L);
        bo1.setFirstName("boat");
        bo1.setLastName("owner");
        boatOwnerService.save(bo1.getId(), bo1);


        Boat b1 = new Boat();
        //b1.setId(1l);
        b1.setName("zBoat name");
        b1.setInfo("boat 1 info");
        b1.setAvgRating(5f);
        b1.setAddress("adresa");
        b1.setOwner(bo1);
        b1.setPrice(10F);
        b1.setNoOfPersons(3);
        b1.setMisc("kapetan-wifi");
        boatService.save(b1.getId(), b1);

        Boat b2 = new Boat();
        //b1.setId(1l);
        b2.setName("Super Kul Brod");
        b2.setInfo("boat 2 info");
        b2.setAvgRating(5f);
        b2.setAddress("badresa");
        b2.setOwner(bo1);
        b2.setPrice(30F);
        b2.setNoOfPersons(3);
        b2.setMisc("");
        boatService.save(b2.getId(), b2);

        Boat b3 = new Boat();
        //b1.setId(1l);
        b3.setName("33333333333");
        b3.setInfo("boat 3 info");
        b3.setAvgRating(3.2f);
        b3.setAddress("adresa");
        b3.setOwner(bo1);
        b3.setPrice(250F);
        b3.setNoOfPersons(3);
        b3.setMisc("");
        boatService.save(b3.getId(), b3);

        Boat b4 = new Boat();
        //b1.setId(1l);
        b4.setName("zzzzzzz");
        b4.setInfo("boat 3 info");
        b4.setAvgRating(3f);
        b4.setAddress("gadresa");
        b4.setOwner(bo1);
        b4.setPrice(111F);
        b4.setNoOfPersons(3);
        b4.setMisc("");
        boatService.save(b4.getId(), b4);

        Instructor i1 = new Instructor();
        //i1.setId(1L);
        i1.setFirstName("ivan");
        i1.setLastName("ivanovic");
        i1.setEmail("super@cool.com");
        i1.setPassword("plaintext");
        instructorService.save(i1.getId(), i1);

        Instructor i2 = new Instructor();
        //i1.setId(1L);
        i2.setFirstName("instruktor");
        i2.setLastName("drugi");
        i2.setEmail("asdf@cool.com");
        i2.setPassword("plaintext");
        instructorService.save(i2.getId(), i2);

        Adventure a1 = new Adventure();
        //a1.setId(1L);
        a1.setName("Adventure 1 name");
        a1.setInfo("Adventure 1 info");
        a1.setAvgRating(2f);
        a1.setPrice(60F);
        a1.setNoOfPersons(4);
        a1.setMisc("");
        a1.setAddress("adresa");
        a1.setInstructor(i1);
        adventureService.save(a1.getId(), a1);




        Adventure a2 = new Adventure();
        //a2.setId(2l);
        a2.setName("Adventure 2 name");
        a2.setInfo("Adventure 2 info");
        a2.setAvgRating(5f);
        a2.setPrice(120F);
        a2.setNoOfPersons(4);
        a2.setMisc("");
        a2.setAddress("adresa");
        a2.setInstructor(i1);
        adventureService.save(a2.getId(), a2);

        Adventure a3 = new Adventure();
        //a2.setId(2l);
        a3.setName("Adventure 3 name");
        a3.setInfo("Adventure 3 info");
        a3.setAvgRating(5f);
        a3.setPrice(120F);
        a3.setNoOfPersons(4);
        a3.setMisc("");
        a3.setAddress("adresa");
        a3.setInstructor(i2);
        adventureService.save(a3.getId(), a3);


        BoatReservation br1 = new BoatReservation();
        br1.setBoat(b2);
        br1.setPrice(78F);
        br1.setUser(user);
        br1.setReservationType(ReservationType.CANCELLED);
        br1.setReservationTime(LocalDateTime.now());
        br1.setDateFrom(LocalDateTime.of(2022, 2, 1, 0, 0));
        br1.setDateEnd(LocalDateTime.of(2022, 2, 6, 0, 0));
        boatReservationService.save(1L, br1);

        BoatReservation brd = new BoatReservation();
        brd.setBoat(b1);
        brd.setUser(null);
        brd.setReservationType(ReservationType.DISCOUNTOFFER);
        brd.setReservationTime(LocalDateTime.now());
        brd.setPrice(2F);
        brd.setDateFrom(LocalDateTime.of(2022, 2, 1, 0, 0));
        brd.setDateEnd(LocalDateTime.of(2022, 2, 6, 0, 0));
        boatReservationService.save(1L, brd);

        BoatReservation br2 = new BoatReservation();
        br2.setBoat(b2);
        br2.setPrice(14F);
        br2.setUser(user);
        br2.setReservationType(ReservationType.CANCELLED);
        br2.setReservationTime(LocalDateTime.now());
        br2.setDateFrom(LocalDateTime.of(2022, 1, 1, 0, 0));
        br2.setDateEnd(LocalDateTime.of(2022, 1, 6, 0, 0));
        boatReservationService.save(1L, br2);

        BoatReservation br3 = new BoatReservation();
        br3.setBoat(b1);
        br3.setPrice(12F);
        br3.setUser(user);
        br3.setReservationTime(LocalDateTime.of(2021,10,28,20,30));
        br3.setDateFrom(LocalDateTime.of(2021, 5, 1, 0, 0));
        br3.setDateEnd(LocalDateTime.of(2021, 5, 6, 0, 0));
        br3.setReservationType(ReservationType.ACTIVE);
        boatReservationService.save(1L, br3);

        BoatReservation br4 = new BoatReservation();
        br4.setBoat(b1);
        br4.setPrice(136F);
        br4.setUser(user);
        br4.setReservationTime(LocalDateTime.of(2022,10,28,20,30));
        br4.setDateFrom(LocalDateTime.of(2022, 11, 1, 0, 0));
        br4.setDateEnd(LocalDateTime.of(2022, 11, 6, 0, 0));
        br4.setReservationType(ReservationType.ACTIVE);
        boatReservationService.save(1L, br4);

        BoatReservation br5 = new BoatReservation();
        br5.setBoat(b1);
        br5.setPrice(123F);
        br5.setUser(user);
        br5.setReservationTime(LocalDateTime.of(2021,11,28,20,30));
        br5.setDateFrom(LocalDateTime.of(2021, 11, 1, 0, 0));
        br5.setDateEnd(LocalDateTime.of(2021, 11, 6, 0, 0));
        br5.setReservationType(ReservationType.ACTIVE);
        boatReservationService.save(1L, br5);


        VacationHouseReservation vhr1 = new VacationHouseReservation();
        vhr1.setVacationHouse(vh1);
        vhr1.setPrice(77.7F);
        vhr1.setUser(user);
        vhr1.setReservationType(ReservationType.ACTIVE);
        vhr1.setReservationTime(LocalDateTime.now());
        vhr1.setDateFrom(LocalDateTime.of(2021, 1, 1, 0, 0));
        vhr1.setDateEnd(LocalDateTime.of(2021, 1, 6, 0, 0));
        vacationHouseReservationService.save(1L, vhr1);

        VacationHouseReservation vhr2 = new VacationHouseReservation();
        vhr2.setVacationHouse(vh1);
        vhr2.setPrice(89F);
        vhr2.setUser(user);
        vhr2.setReservationType(ReservationType.ACTIVE);
        vhr2.setReservationTime(LocalDateTime.now());
        vhr2.setDateFrom(LocalDateTime.of(2021, 2, 1, 0, 0));
        vhr2.setDateEnd(LocalDateTime.of(2021, 2, 6, 0, 0));
        vacationHouseReservationService.save(1L, vhr2);

        VacationHouseReservation vhrd = new VacationHouseReservation();
        vhrd.setVacationHouse(vh1);
        vhrd.setUser(null);
        vhrd.setReservationType(ReservationType.DISCOUNTOFFER);
        vhrd.setReservationTime(LocalDateTime.now());
        vhrd.setPrice(10F);
        vhrd.setDateFrom(LocalDateTime.of(2021, 9, 1, 0, 0));
        vhrd.setDateEnd(LocalDateTime.of(2021, 9, 6, 0, 0));
        vacationHouseReservationService.save(1L, vhrd);

        AdventureReservation ar1 = new AdventureReservation();
        ar1.setAdventure(a1);
        ar1.setPrice(74F);
        ar1.setUser(user);
        ar1.setReservationType(ReservationType.ACTIVE);
        ar1.setReservationTime(LocalDateTime.now());
        ar1.setDateFrom(LocalDateTime.of(2022, 2, 1, 0, 0));
        ar1.setDateEnd(LocalDateTime.of(2022, 3, 6, 0, 0));
        adventureReservationService.save(1L, ar1);

        AdventureReservation ar2 = new AdventureReservation();
        ar2.setAdventure(a2);
        ar2.setPrice(82F);
        ar2.setUser(user);
        ar2.setReservationType(ReservationType.ACTIVE);
        ar2.setReservationTime(LocalDateTime.now());
        ar2.setDateFrom(LocalDateTime.of(2022, 1, 1, 0, 0));
        ar2.setDateEnd(LocalDateTime.of(2022, 1, 6, 0, 0));
        adventureReservationService.save(1L, ar2);

        AdventureReservation ar3 = new AdventureReservation();
        ar3.setAdventure(a2);
        ar3.setPrice(39.99F);
        ar3.setUser(user);
        ar3.setReservationType(ReservationType.ACTIVE);
        ar3.setReservationTime(LocalDateTime.now());
        ar3.setDateFrom(LocalDateTime.of(2022, 5, 6, 0, 0));
        ar3.setDateEnd(LocalDateTime.of(2022, 5, 16, 0, 0));
        adventureReservationService.save(1L, ar3);

        AdventureReservation ar4 = new AdventureReservation();
        ar4.setAdventure(a3);
        ar4.setPrice(404F);
        ar4.setUser(user);
        ar4.setReservationType(ReservationType.ACTIVE);
        ar4.setReservationTime(LocalDateTime.now());
        ar4.setDateFrom(LocalDateTime.of(2022, 5, 6, 0, 0));
        ar4.setDateEnd(LocalDateTime.of(2022, 5, 16, 0, 0));
        adventureReservationService.save(1L, ar4);

        AdventureReservation ar5 = new AdventureReservation();
        ar5.setAdventure(a1);
        ar5.setPrice(15F);
        ar5.setUser(user);
        ar5.setReservationType(ReservationType.ACTIVE);
        ar5.setReservationTime(LocalDateTime.now());
        ar5.setDateFrom(LocalDateTime.of(2021, 5, 6, 0, 0));
        ar5.setDateEnd(LocalDateTime.of(2021, 5, 16, 0, 0));
        adventureReservationService.save(1L, ar5);

        AdventureReservation ard = new AdventureReservation();
        ard.setAdventure(a1);
        ard.setUser(null);
        ard.setReservationType(ReservationType.DISCOUNTOFFER);
        ard.setPrice(1F);
        ard.setReservationTime(LocalDateTime.now());
        ard.setDateFrom(LocalDateTime.of(2022, 3, 6, 0, 0));
        ard.setDateEnd(LocalDateTime.of(2022, 3, 16, 0, 0));
        adventureReservationService.save(1L, ard);




    }
}
