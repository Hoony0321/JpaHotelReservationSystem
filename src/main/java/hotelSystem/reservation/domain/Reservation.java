package hotelSystem.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) //생성자 막기
public class Reservation {

    @Id @GeneratedValue
    @Column(name = "reservation_id")
    private Long id;

    @Column(name = "check_in")
    private LocalDate checkInDate;
    @Column(name = "check_out")
    private LocalDate checkOutDate;
    private ReservationStatus reservationStatus;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") //연관관계 주인
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id") //연관관계 주인
    private Room room;

    //=== 생성 매서드 ====//
    public Reservation(Customer customer, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        setCustomer(customer);
        setRoom(room);
        this.reservationStatus = ReservationStatus.STANDBY;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    //=== 연관관계 편의 매서드 ===//
    public void setCustomer(Customer customer){
        this.customer = customer;
        customer.getReservations().add(this);
    }

    public void setRoom(Room room){
        this.room = room;
    }

    //=== 비즈니스 로직 ===//
    public void approval(){
        this.reservationStatus =  ReservationStatus.APPROVAL;
    }

    public void denial(){
        this.reservationStatus = ReservationStatus.DENIAL;
    }

}
