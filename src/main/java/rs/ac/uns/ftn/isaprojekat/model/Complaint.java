package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public class Complaint extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @Column(columnDefinition ="TEXT")
    private String content;
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
