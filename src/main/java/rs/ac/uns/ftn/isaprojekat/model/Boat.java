package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="boats")
public class Boat extends Offer {

    @ManyToOne
    @NotNull
    @JoinColumn(name="boat_owner_id")
    private BoatOwner boatOwner;

    public BoatOwner getOwner() {
        return boatOwner;
    }

    public void setOwner(BoatOwner boatOwner) {
        this.boatOwner = boatOwner;
    }
}
