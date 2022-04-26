package hotelSystem.reservation.Service;

import hotelSystem.reservation.domain.Customer;
import hotelSystem.reservation.domain.Reservation;
import hotelSystem.reservation.domain.Room;
import hotelSystem.reservation.repository.ReservationRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired private ReservationService reservationService;
    @Autowired private ReservationRepository reservationRepository;

    @Test
    public void 예약하기() throws Exception{
        //given
        Customer customer = new Customer("customer1", "01011111111");
        Room room = new Room("room1" , 15000, 10);
        Reservation reservation = new Reservation(customer, room, LocalDate.of(2022, 04, 20), LocalDate.of(2022, 04, 25));

        //when
        Long id = reservationService.join(reservation);

        //then
        Reservation findObject = reservationRepository.findOne(id);
        assertThat(reservation.getRoom()).isEqualTo(findObject.getRoom());

    }

    @Test
    public void 중복날짜_예약() throws Exception{
        //given
        Customer customer1 = new Customer("customer1", "01011111111");
        Room room1 = new Room("room1" , 15000, 10);
        Reservation reservation1 = new Reservation(customer1, room1, LocalDate.of(2022, 04, 20), LocalDate.of(2022, 04, 25));
        reservationService.join(reservation1);

        Customer customer2 = new Customer("customer2", "01022222222");
        Reservation reservation2 = new Reservation(customer2, room1, LocalDate.of(2022, 04, 18), LocalDate.of(2022, 04, 23));

        //when
        IllegalStateException error = assertThrows(IllegalStateException.class, () -> {reservationService.join(reservation2);});

        //then
        assertThat(error.getMessage()).isEqualTo("이미 해당 기간에 예약이 잡혀있습니다.");


     }
}