package rs.ac.uns.ftn.isaprojekat.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "deletion_requests")
public class DeletionRequest extends BaseEntity{

    @OneToOne
    @JoinColumn(name="user_id")
    @NotNull
    private User user;
    @Column(columnDefinition ="TEXT")
    private String text;
    @NotNull
    private LocalDate dateOfRequest;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateOfRequest() {
        return dateOfRequest;
    }

    public void setDateOfRequest(LocalDate dateOfRequest) {
        this.dateOfRequest = dateOfRequest;
    }
}
