package hotelSystem.reservation.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationSearch {


    private String customerName;
    private String roomName;
    private ReservationStatus reservationStatus;

}
