package hotelSystem.reservation.Service;

import hotelSystem.reservation.domain.Reservation;
import hotelSystem.reservation.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
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

}
