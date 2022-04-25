package hotelSystem.reservation.repository;

import hotelSystem.reservation.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class RoomRepository {

    private final EntityManager em;

    @Autowired public RoomRepository(EntityManager em) {this.em = em;}

    public void save(Room room){
        em.persist(room);
    }

    public Room findOne(Long id){
        return em.find(Room.class, id);
    }

    public List<Room> findAll(){
        return em.createQuery("select r from Reservation r", Room.class)
                     .getResultList();
    }




}
