package hotelSystem.reservation.controller.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomForm {
    private String name;
    private Integer price;
    private Integer capacity;
}
