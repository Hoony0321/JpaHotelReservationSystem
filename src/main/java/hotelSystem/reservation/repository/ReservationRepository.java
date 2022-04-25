package hotelSystem.reservation.repository;

import hotelSystem.reservation.ReservationApplication;
import hotelSystem.reservation.domain.Customer;
import hotelSystem.reservation.domain.Reservation;
import hotelSystem.reservation.domain.ReservationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReservationRepository {

    private final EntityManager em;

    @Autowired public ReservationRepository(EntityManager em) {this.em = em;}

    public void save(Reservation reservation){
        em.persist(reservation);
    }

    public Reservation findOne(Long id){
        return em.find(Reservation.class, id);
    }

    public List<Reservation> findAll(){
        return em.createQuery("select r from Reservation r", Reservation.class)
                .getResultList();
    }

    public List<Reservation> findByCustomerName(Customer customer){
        return em.createQuery("select r from Reservation r where r.customer = :customer", Reservation.class) //이후에 customer.id로도 한번 해보기
                .setParameter("customer", customer)
                .getResultList();
    }

    public List<Reservation> findByStatus(ReservationStatus status){
        return em.createQuery("select r from Reservation r where r.reservationStatus = :status",  Reservation.class)
                .setParameter("status", status)
                .getResultList();
    }
}
