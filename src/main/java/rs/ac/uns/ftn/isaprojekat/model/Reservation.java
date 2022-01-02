package rs.ac.uns.ftn.isaprojekat.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public class Reservation extends BaseEntity {

    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;

    private LocalDateTime dateFrom;
    private LocalDateTime dateEnd;
    private LocalDateTime reservationTime;

    @Enumerated(EnumType.STRING)
    private ReservationType reservationType;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalDateTime getReservationTime() {
        return reservationTime;
    }

    public void setReservationTime(LocalDateTime reservationTime) {
        this.reservationTime = reservationTime;
    }

    public ReservationType getReservationType() {
        return reservationType;
    }

    public void setReservationType(ReservationType reservationType) {
        this.reservationType = reservationType;
    }
}

