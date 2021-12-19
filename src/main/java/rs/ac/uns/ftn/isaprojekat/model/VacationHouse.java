package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vacation_houses")
public class VacationHouse extends Offer {

    @ManyToOne
    @JoinColumn(name="owner_id")
    private VacationHouseOwner vacationHouseOwner;

    public VacationHouseOwner getVacationHouseOwner() {
        return vacationHouseOwner;
    }

    public void setVacationHouseOwner(VacationHouseOwner vacationHouseOwner) {
        this.vacationHouseOwner = vacationHouseOwner;
    }
}
