package hotelSystem.reservation.repository;

import hotelSystem.reservation.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerRepository {

    private final EntityManager em;

    @Autowired
    public CustomerRepository(EntityManager em) {
        this.em = em;
    }

    public void save(Customer customer){
        em.persist(customer);
    }

    public Customer findOne(Long id){
        return em.find(Customer.class, id);
    }

    public List<Customer> findAll(){
        List<Customer> customers = em.createQuery("select c from Customer c", Customer.class)
                .getResultList();
        return customers;
    }

    public List<Customer> findByNameAndPhoneNumber(Customer customer){
        return em.createQuery("select c from Customer c where c.name = :name and c.phoneNumber = :phoneNumber", Customer.class)
                    .setParameter("name", customer.getName())
                    .setParameter("phoneNumber", customer.getPhoneNumber())
                    .getResultList();
    }


}
