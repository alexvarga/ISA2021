package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="boat_complaints")
public class BoatComplaint extends Complaint {

    @ManyToOne
    @JoinColumn(name="boat_id")
    private Boat boat;

    public Boat getBoat() {
        return boat;
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
