package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacation_house_subscriptions")
public class VacationHouseSubscription extends Subscription {

    @ManyToOne
    @JoinColumn(name="vacation_house_id")
    private VacationHouse vacationHouse;

    public VacationHouse getVacationHouse() {
        return vacationHouse;
    }

    public void setVacationHouse(VacationHouse vacationHouse) {
        this.vacationHouse = vacationHouse;
    }
}