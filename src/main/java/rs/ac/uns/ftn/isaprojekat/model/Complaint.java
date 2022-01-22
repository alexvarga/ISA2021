package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@MappedSuperclass
public class Complaint extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    @NotNull
    private User user;
    @Column(columnDefinition ="TEXT")
    private String content;
    @NotNull
    private LocalDate complaintDate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }
}
