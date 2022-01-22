package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
public class Subscription extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @NotNull
    private LocalDate dateOfSubscribing;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDateOfSubscribing() {
        return dateOfSubscribing;
    }

    public void setDateOfSubscribing(LocalDate dateOfSubscribing) {
        this.dateOfSubscribing = dateOfSubscribing;
    }
}
