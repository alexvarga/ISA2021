package rs.ac.uns.ftn.isaprojekat.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isaprojekat.model.*;
import rs.ac.uns.ftn.isaprojekat.service.*;

@Component
public class DataLoader implements CommandLineRunner {

    private final VacationHouseService vacationHouseService;
    private final BoatService boatService;
    private final AdventureService adventureService;
    private final InstructorService instructorService;
    private final VacationHouseOwnerService vacationHouseOwnerService;
    private final BoatOwnerService boatOwnerService;
    private final UserService userService;


    @Autowired
    public DataLoader(VacationHouseService vacationHouseService, BoatService boatService, AdventureService adventureService, InstructorService instructorService, VacationHouseOwnerService vacationHouseOwnerService, BoatOwnerService boatOwnerService, UserService userService) {
        this.vacationHouseService = vacationHouseService;
        this.boatService = boatService;
        this.adventureService = adventureService;
        this.instructorService = instructorService;
        this.vacationHouseOwnerService = vacationHouseOwnerService;
        this.boatOwnerService = boatOwnerService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {

        User user = new User();
        user.setEmail("user2");
        user.setPassword("test2");

        user.setEnabled(true);
        user.setLocked(false);
        user.setUserRole(UserRole.ADMIN);
        user.setPassword("testing");
        userService.save(user.getId(), user);


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
        System.out.println("saved vh1");


        VacationHouse vh2 = new VacationHouse();
        //vh2.setId(2L);
        vh2.setName("Nova vikendinca 2");
        vh2.setInfo("info o novoj vikendici 2");
        vh2.setAvgRating(5f);
        vh2.setAddress("adresa 2");
        vh2.setVacationHouseOwner(vho1);

        vacationHouseService.save(vh2.getId(), vh2);
        System.out.println("saved vh2");


        VacationHouse vh3 = new VacationHouse();
        //vh3.setId(3L);
        vh3.setName("Nova vikendinca 3");
        vh3.setInfo("info o novoj vikendici 3");
        vh3.setAvgRating(5f);
        vh3.setAddress("adresa 3");

        vacationHouseService.save(vh3.getId(), vh3);
        System.out.println("saved vh3");

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
        System.out.println("saved b1");

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
        System.out.println("saved a1");


       //instructorService.save(i1.getId(), i1);
        System.out.println("saved instructor");


        Adventure a2 = new Adventure();
        //a2.setId(2l);
        a2.setName("Adventure 2 name");
        a2.setInfo("Adventure 2 info");
        a2.setAvgRating(5f);
        a2.setAddress("adresa");

        a2.setInstructor(i1);
        adventureService.save(a2.getId(), a2);
        System.out.println("saved a2");



    }
}
