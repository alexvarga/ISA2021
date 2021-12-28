package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="vacation_house_owners")
public class VacationHouseOwner extends Person {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vacationHouseOwner")
    private Set<VacationHouse> vacationHouses;

    public Set<VacationHouse> getVacationHouses() {
        return vacationHouses;
    }

    public void setVacationHouses(Set<VacationHouse> vacationHouses) {
        this.vacationHouses = vacationHouses;
    }
}
