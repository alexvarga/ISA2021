package rs.ac.uns.ftn.isaprojekat.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends Person  {



    @Enumerated(EnumType.STRING)
    private UserRole userRole;
    private Boolean enabled;
    private Boolean locked;

    private String verificationCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<VacationHouseReservation> vacationHouseReservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<BoatReservation> boatReservations = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<AdventureReservation> adventureReservations = new HashSet<>();

    public User() {
    }

    public User(UserRole userRole, Boolean enabled, Boolean locked, String email,
                String password, String firstName, String lastName, String username, String verificationCode) {
        this.userRole = userRole;
        this.enabled = enabled;
        this.locked = locked;
        this.verificationCode = verificationCode;
        //this.visitedStatus = visitedStatus;
        super.setEmail(email);
        super.setPassword(password);
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setLastName(lastName);
        super.setUsername(username);
    }



    @Override
    public String getUsername() {
        return super.getUsername();
    }


    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Set<VacationHouseReservation> getVacationHouseReservations() {
        return vacationHouseReservations;
    }

    public void setVacationHouseReservations(Set<VacationHouseReservation> vacationHouseReservations) {
        this.vacationHouseReservations = vacationHouseReservations;
    }



    public Set<BoatReservation> getBoatReservations() {
        return boatReservations;
    }

    public void setBoatReservations(Set<BoatReservation> boatReservations) {
        this.boatReservations = boatReservations;
    }

    public Set<AdventureReservation> getAdventureReservations() {
        return adventureReservations;
    }

    public void setAdventureReservations(Set<AdventureReservation> adventureReservations) {
        this.adventureReservations = adventureReservations;
    }
}
