package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class Subscription extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
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
