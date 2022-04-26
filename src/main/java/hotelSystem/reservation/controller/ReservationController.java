package hotelSystem.reservation.controller;

import hotelSystem.reservation.Service.CustomerService;
import hotelSystem.reservation.Service.ReservationService;
import hotelSystem.reservation.Service.RoomService;
import hotelSystem.reservation.controller.form.ReservationForm;
import hotelSystem.reservation.domain.Customer;
import hotelSystem.reservation.domain.Reservation;
import hotelSystem.reservation.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerService customerService;
    private final RoomService roomService;

    @Autowired
    public ReservationController(ReservationService reservationService, CustomerService customerService, RoomService roomService) {
        this.reservationService = reservationService;
        this.customerService = customerService;
        this.roomService = roomService;
    }

    @GetMapping("/reservations")
    public String reservationsList(Model model){
        List<Reservation> reservations = reservationService.findReservations();
        model.addAttribute("reservations", reservations);
        return "reservations/reservationList";
    }

    @GetMapping("/reservations/new")
    public String createNewReservation(Model model){
        List<Customer> customers = customerService.findCustomers();
        List<Room> rooms = roomService.findRooms();

        model.addAttribute("customers", customers);
        model.addAttribute("rooms", rooms);

        return "reservations/createReservationForm";
    }

    @PostMapping("/reservations/new")
    public String create(ReservationForm form){
        Customer customer = customerService.findCustomer(form.getCustomer_id());
        Room room = roomService.findRoom(form.getRoom_id());

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate checkInDate = LocalDate.parse(form.getCheckInDate(), dateTimeFormatter);
        LocalDate checkOutDate = LocalDate.parse(form.getCheckOutDate(), dateTimeFormatter);

        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationService.join(reservation);
        return "redirect:/";
    }

}
