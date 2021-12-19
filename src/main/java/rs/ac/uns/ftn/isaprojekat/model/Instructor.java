package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="instructors")
public class Instructor extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "instructor")
    private Set<Adventure> adventures;

    public Set<Adventure> getAdventures() {
        return adventures;
    }

    public void setAdventures(Set<Adventure> adventures) {
        this.adventures = adventures;
    }
}
