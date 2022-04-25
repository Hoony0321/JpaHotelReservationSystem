package hotelSystem.reservation.Service;

import hotelSystem.reservation.domain.Room;
import hotelSystem.reservation.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoomService {

    private final RoomRepository roomRepository;

    @Autowired public RoomService(RoomRepository roomRepository) {this.roomRepository = roomRepository;}

    @Transactional
    public Long join(Room room){
        roomRepository.save(room);
        return room.getId();
    }

    public Room findRoom(Long id){
        return roomRepository.findOne(id);
    }

    public List<Room> findRooms(){
        return roomRepository.findAll();
    }

}
