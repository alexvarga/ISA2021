package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="boat_owners")
public class BoatOwner extends Person{

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "boatOwner")
    private Set<Boat> boats;

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }
}
