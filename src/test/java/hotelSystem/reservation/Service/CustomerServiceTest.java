package hotelSystem.reservation.Service;

import hotelSystem.reservation.domain.Customer;
import hotelSystem.reservation.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CustomerServiceTest {

    @Autowired private CustomerService customerService;
    @Autowired private CustomerRepository customerRepository;

    @Test
    public void 회원가입() throws Exception{
        //given
        Customer customer = Customer.createNewCustomer("test","010-1111-1111");

        //when
        Long saveId = customerService.join(customer);

        //then
        Customer findOne = customerRepository.findOne(saveId);
        assertThat(findOne).isEqualTo(customer);

    }

    @Test
    public void validateDuplicateCustomer() {
        //given
        Customer customer = Customer.createNewCustomer("test","010-1111-1111");
        Long saveId = customerService.join(customer);

        Customer customer1 = Customer.createNewCustomer("test","010-1111-1111");

        //when
        IllegalStateException error = assertThrows(IllegalStateException.class, () -> {
            customerService.join(customer1);
        });

        //then
        assertThat(error.getMessage()).isEqualTo("이미 존재하는 고객입니다.");
    }

}