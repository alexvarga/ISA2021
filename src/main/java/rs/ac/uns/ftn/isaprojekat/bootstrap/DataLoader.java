package rs.ac.uns.ftn.isaprojekat.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.isaprojekat.model.Boat;
import rs.ac.uns.ftn.isaprojekat.model.VacationHouse;
import rs.ac.uns.ftn.isaprojekat.service.BoatService;
import rs.ac.uns.ftn.isaprojekat.service.VacationHouseService;

@Component
public class DataLoader implements CommandLineRunner {

    private final VacationHouseService vacationHouseService;
    private final BoatService boatService;

    @Autowired
    public DataLoader(VacationHouseService vacationHouseService, BoatService boatService) {
        this.vacationHouseService = vacationHouseService;
        this.boatService = boatService;
    }

    @Override
    public void run(String... args) throws Exception {

        VacationHouse vh1 = new VacationHouse();
        vh1.setId(1L);
        vh1.setName("Nova vikendinca 1");
        vh1.setInfo("info o novoj vikendici 1");
        vh1.setAvgRating(2.5f);
        vh1.setAddress("adresa 1");

        vacationHouseService.save(vh1.getId(), vh1);
        System.out.println("saved vh1");

        VacationHouse vh2 = new VacationHouse();
        vh2.setId(2L);
        vh2.setName("Nova vikendinca 2");
        vh2.setInfo("info o novoj vikendici 2");
        vh2.setAvgRating(5f);
        vh2.setAddress("adresa 2");

        vacationHouseService.save(vh2.getId(), vh2);
        System.out.println("saved vh2");


        Boat b1 = new Boat();
        b1.setId(1l);
        b1.setName("Boat name");
        b1.setInfo("boat 1 info");
        b1.setAvgRating(5f);
        vh2.setAddress("adresa");
        boatService.save(b1.getId(), b1);
        System.out.println("saved b1");


    }
}
