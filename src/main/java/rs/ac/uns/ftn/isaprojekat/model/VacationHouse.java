package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="vacation_houses")
public class VacationHouse extends Offer {

    @ManyToOne
    @JoinColumn(name="owner_id")
    private VacationHouseOwner vacationHouseOwner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacationHouse")
    private Set<VacationHouseReservation> vacationHouseReservations;

    public VacationHouseOwner getVacationHouseOwner() {
        return vacationHouseOwner;
    }

    public void setVacationHouseOwner(VacationHouseOwner vacationHouseOwner) {
        this.vacationHouseOwner = vacationHouseOwner;
    }

    public Set<VacationHouseReservation> getVacationHouseReservations() {
        return vacationHouseReservations;
    }

    public void setVacationHouseReservations(Set<VacationHouseReservation> vacationHouseReservations) {
        this.vacationHouseReservations = vacationHouseReservations;
    }
}
