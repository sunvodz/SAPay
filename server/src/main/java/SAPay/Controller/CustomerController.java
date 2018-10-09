package SAPay.Controller;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import SAPay.entity.*;
import SAPay.Repository.*;
import java.util.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CustomerController {
        
        @Autowired 
        private CustomerRepository customerRepository;
        public CustomerController(CustomerRepository customerRepository) {
            this.customerRepository = customerRepository;
        }
        @GetMapping("/customer")
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