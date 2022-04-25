package hotelSystem.reservation.controller;

import hotelSystem.reservation.Service.RoomService;
import hotelSystem.reservation.controller.form.RoomForm;
import hotelSystem.reservation.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RoomController {

    private final RoomService roomService;

    @Autowired public RoomController(RoomService roomService) {this.roomService = roomService;}

    @GetMapping("/rooms/new")
    public String createRoomForm(){
        return "/rooms/createRoomForm";
    }

    @PostMapping("/rooms/new")
    public String create(RoomForm form){
        Room newRoom = Room.createNewRoom(form.getName(), form.getPrice(), form.getCapacity());
        roomService.join(newRoom);
        return "redirect:/";
    }




}
