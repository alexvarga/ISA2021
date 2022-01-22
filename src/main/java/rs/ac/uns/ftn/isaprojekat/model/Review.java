package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
public class Review extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @NotNull
    private Float rating;
    @NotNull
    @Column(columnDefinition ="TEXT")
    private String content;
    @NotNull
    private LocalDateTime reviewTime;

    @NotNull
    @Enumerated
    private ReviewStatus reviewStatus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ReviewStatus getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(ReviewStatus reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }
}
