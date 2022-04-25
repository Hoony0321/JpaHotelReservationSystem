package hotelSystem.reservation.controller;

import hotelSystem.reservation.Service.CustomerService;
import hotelSystem.reservation.controller.form.CustomerForm;
import hotelSystem.reservation.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired public CustomerController(CustomerService customerService) {this.customerService = customerService;}

    @GetMapping("/customers/new")
    public String createCustomerForm(){
        return "customers/createCustomerForm";
    }

    @PostMapping("/customers/new")
    public String create(CustomerForm form){
        Customer newCustomer = Customer.createNewCustomer(form.getName(), form.getPhoneNumber());
        customerService.join(newCustomer);
        return "redirect:/";
    }

    @GetMapping("/customers")
    public String customerList(Model model){
        List<Customer> customers = customerService.findCustomers();
        model.addAttribute("customers", customers);
        return "customers/customerList";
    }

}
