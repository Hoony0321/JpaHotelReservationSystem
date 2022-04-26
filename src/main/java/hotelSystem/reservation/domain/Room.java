package hotelSystem.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {

    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @Column(name = "room_name")
    private String name;
    private Integer price;
    private Integer capacity;


    public Room(String name, Integer price, Integer capacity){
        this.name = name;
        this.price = price;
        this.capacity = capacity;
    }
}
