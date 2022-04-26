package hotelSystem.reservation.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
public class ReservationForm {

    private Long customer_id;
    private Long room_id;
    private String checkInDate;
    private String checkOutDate;

}
