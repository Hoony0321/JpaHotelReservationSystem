package hotelSystem.reservation.Service;

import hotelSystem.reservation.domain.Reservation;
import hotelSystem.reservation.domain.ReservationSearch;
import hotelSystem.reservation.repository.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired public ReservationService(ReservationRepository reservationRepository) {this.reservationRepository = reservationRepository;}

    @Transactional
    public Long join(Reservation reservation){
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public Reservation findReservation(Long id){
        return reservationRepository.findOne(id);
    }

    public List<Reservation> findReservations(){
        return reservationRepository.findAll();
    }

    public List<Reservation> searchByCondition(ReservationSearch reservationSearch){
        return reservationRepository.searchByCondition(reservationSearch);
    }

    @Transactional
    public void approval(Long id){
        Reservation getObject = reservationRepository.findOne(id);
        getObject.approval();
    }

    @Transactional
    public void denial(Long id){
        Reservation getObject = reservationRepository.findOne(id);
        getObject.denial();
    }

}
