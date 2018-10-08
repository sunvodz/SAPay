package SAPay.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;

import SAPay.entity.Customer;
import  SAPay.Repository.CustomerRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
        private CustomerRepository customerRepository;

        public CustomerController(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }
        @RequestMapping("/customer")
        public Collection<Customer> customer() {
        return customerRepository.findAll().stream()
                .filter(this::isCustomer)
                .collect(Collectors.toList());
    }
        private boolean isCustomer(Customer customer){
            return customer.getCustomerName().equals("Sunvo") ||
                    customer.getCustomerName().equals("Ploy") ||
                    customer.getCustomerName().equals("Ao") ;
    }
}