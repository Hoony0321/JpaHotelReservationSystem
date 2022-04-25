package hotelSystem.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter(value = AccessLevel.PROTECTED)
public class Room {

    @Id @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    @Column(name = "room_name")
    private String name;
    private Integer price;
    private Integer capacity;

    public static Room createNewRoom(String name, Integer price, Integer capacity){
        Room newRoom = new Room();
        newRoom.setName(name);
        newRoom.setPrice(price);
        newRoom.setCapacity(capacity);

        return newRoom;
    }
}
