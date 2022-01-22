package rs.ac.uns.ftn.isaprojekat.model;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "income_rate")
public class IncomeRate extends BaseEntity {

    @NotNull
    private String entityName;
    @NotNull
    private Float entityPercent;


    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Float getEntityPercent() {
        return entityPercent;
    }

    public void setEntityPercent(Float entityPercent) {
        this.entityPercent = entityPercent;
    }
}
