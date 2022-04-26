package hotelSystem.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations = new ArrayList<>();

    public Customer(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
}
