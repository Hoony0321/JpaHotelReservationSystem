package hotelSystem.reservation.repository;

import hotelSystem.reservation.ReservationApplication;
import hotelSystem.reservation.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
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

    public List<Reservation> searchByCondition(ReservationSearch reservationSearch) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Reservation> rq = cb.createQuery(Reservation.class);
        Root<Reservation> r = rq.from(Reservation.class);
        Join<Reservation, Customer> rc = r.join("customer", JoinType.INNER); //고객과 조인
        Join<Reservation, Room> rr = r.join("room", JoinType.INNER); //방과 조인
        List<Predicate> criteria = new ArrayList<>();
        //주문 상태 검색
        if (reservationSearch.getReservationStatus() != null) {
            Predicate status = cb.equal(r.get("reservationStatus"),
                    reservationSearch.getReservationStatus());
            criteria.add(status);
        }
        //고객 이름 검색
        if (StringUtils.hasText(reservationSearch.getCustomerName())) {
            Predicate customerName =
                    cb.like(rc.<String>get("name"), "%" +
                            reservationSearch.getCustomerName() + "%");
            criteria.add(customerName); }
        //방 이름 검색
        if (StringUtils.hasText(reservationSearch.getRoomName())) {
            Predicate roomName =
                    cb.like(rr.<String>get("name"), "%" +
                            reservationSearch.getRoomName() + "%");
            criteria.add(roomName); }

        rq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Reservation> query = em.createQuery(rq).setMaxResults(1000); //최대1000건
        return query.getResultList();
    }
}
