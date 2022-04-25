package hotelSystem.reservation.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Room {

    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @Column(name = "room_name")
    private String name;
    private Integer price;
    private Integer capacity;
}
