package hotelSystem.reservation.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Customer {

    @Id @GeneratedValue
    @Column(name = "customer_id")
    private Long id;

    private String name;
    private String phoneNumber;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservations = new ArrayList<>();

    public static Customer createNewCustomer(String name, String phoneNumber){
        Customer customer = new Customer();
        customer.setName(name);
        customer.setPhoneNumber(phoneNumber);

        return customer;
    }
}
