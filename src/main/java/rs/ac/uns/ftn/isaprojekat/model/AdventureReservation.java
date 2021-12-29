package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="adventure_reservations")
public class AdventureReservation extends Reservation {

    @ManyToOne
    @JoinColumn(name="adventure_id")
    private Adventure adventure;

    public Adventure getAdventure() {
        return adventure;
    }

    public void setAdventure(Adventure adventure) {
        this.adventure = adventure;
    }
}
