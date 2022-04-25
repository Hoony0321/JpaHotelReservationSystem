package hotelSystem.reservation.domain;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    private LocalDateTime reservationDate;
    private ReservationStatus reservationStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

}
