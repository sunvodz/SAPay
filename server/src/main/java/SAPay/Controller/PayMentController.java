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
public class PayMentController {
        
        @Autowired 
        private PayMentRepository paymentRepository;
        @Autowired
        private StaffRepository staffRepository;
        @Autowired
        private CustomerRepository customerRepository;
        @Autowired
        private BookingRepository bookingRepository;
        @Autowired
        private SellingRepository sellingRepository;
        @Autowired
        private LeaseRepository leaseRepository;

        public PayMentController(PayMentRepository paymentRepository,
                                 StaffRepository staffRepository,
                                 CustomerRepository customerRepository,
                                 BookingRepository bookingRepository,
                                 SellingRepository sellingRepository,
                                 LeaseRepository leaseRepository) {
            this.paymentRepository = paymentRepository;
            this.staffRepository = staffRepository;
            this.customerRepository = customerRepository;
            this.bookingRepository = bookingRepository;
            this.sellingRepository = sellingRepository;
            this.leaseRepository = leaseRepository;
        }
        
        @GetMapping("/payment")
        public Collection<PayMent> payment() {
            return paymentRepository.findAll().stream()
                    .filter(this::isPayMent)
                    .collect(Collectors.toList());
        }
        private boolean isPayMent(PayMent payMent){
            return payMent.getStatusPay().equals("paid");
        }

        @GetMapping("/selling/{customerID}")
        public Selling retrieveSelling(@PathVariable String customerID) {
            Customer customerid = customerRepository.findByCustomerID(customerID);
            Optional<Selling> selling = sellingRepository.findByCustomer(customerid);
            return selling.get();	    
        }



        
        @GetMapping(path = "/lease/{customerID}")
        public Collection <Lease> Lease(@PathVariable String customerID) {
            System.out.println(customerID);
            Customer customerid = customerRepository.findByCustomerID(customerID);
            Collection<Lease> S =  leaseRepository.findByCustomer(customerid);
            System.out.println(S);
        return S;

    }

        @GetMapping("/booking/{customerID}")
        public Booking retrieveBooking(@PathVariable String customerID) {
            Customer customerid = customerRepository.findByCustomerID(customerID);
            Optional<Booking> booking = bookingRepository.findByCustomer(customerid);
            
	    return booking.get();
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
        @GetMapping("/staff")
        public Collection<Staff> staff() {
            return staffRepository.findAll().stream()
                    .filter(this::isStaff)
                    .collect(Collectors.toList());
        }
            private boolean isStaff(Staff staff){
                return staff.getStaffName().equals("Admin");
        }
        @PostMapping("/payment/{payid}/{typepay}/{statuspay}/{staff}/{customer}")
        public PayMent newPayMent(@PathVariable String payid,@PathVariable String statuspay,
                            @PathVariable  String staffids,@PathVariable String customerids,
                            @PathVariable String typepay){
        PayMent newPayMent = new PayMent();
        newPayMent.setStatusPay(statuspay);
        newPayMent.setPaymentIDs(payid);
        newPayMent.setTypePay(typepay);
        
        Date datePay = new Date();
        newPayMent.setDatePay(datePay);

        Staff staffid = staffRepository.findByStaffIDs(staffids);
        newPayMent.setStaff(staffid);

        Customer customerid = customerRepository.findByCustomerID(customerids);
        newPayMent.setCustomer(customerid);
        
        return paymentRepository.save(newPayMent); 
    }

    @PutMapping("/booking/{id}/{statusBooking}")
    Booking replaceBooking(Booking newBooking, @PathVariable String statusBooking, @PathVariable Long id){

        return bookingRepository.findById(id)
                    .map(booking ->{
                    booking.setStatusBooking(statusBooking);
                    return bookingRepository.save(booking);
                }
                ).orElseGet(() ->{
                    newBooking.setBkId(id);
                    return bookingRepository.save(newBooking);
        });
}
    @PutMapping("/selling/{id}/{statusSelling}")
    Selling replaceSelling(Selling newSelling, @PathVariable String statusSelling, @PathVariable Long id){

    return sellingRepository.findById(id)
                .map(selling ->{
                selling.setStatusSelling(statusSelling);
                return sellingRepository.save(selling);
            }
            ).orElseGet(() ->{
                newSelling.setSeid(id);
                return sellingRepository.save(newSelling);
    });
}
    @PutMapping("/lease/{id}/{statusLease}")
    Lease replaceLease(Lease newLease, @PathVariable String statusLease, @PathVariable Long id){

    return leaseRepository.findById(id)
                .map(lease ->{
                lease.setStatusLease(statusLease);
                return leaseRepository.save(lease);
            }
            ).orElseGet(() ->{
                newLease.setLid(id);
                return leaseRepository.save(newLease);
    });
}


}