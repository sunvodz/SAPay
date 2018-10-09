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
                    .collect(Collectors.toList());
    }
    @PostMapping("/payment/{payid}/{statuspay}/datePay/{staff}/{customer}/{booking}/{selling}/{lease}")
    public PayMent newPayMent(@PathVariable String payid,@PathVariable String statuspay,String datePay,
                            @PathVariable  String staff,@PathVariable String customer,@PathVariable String booking,
                            @PathVariable  String selling,@PathVariable  String lease){
        PayMent newPayMent = new PayMent();
        newPayMent.setStatusPay(statuspay);
        newPayMent.setPaymentIDs(payid);
        
        Date datePays = new Date();
        newPayMent.setDatePay(datePays);

        Staff staffid = staffRepository.findByStaffIDs(staff);
        newPayMent.setStaff(staffid);

        Customer customername = customerRepository.findByCustomerName(customer);
        newPayMent.setCustomer(customername); 
        
        Booking bookingid = bookingRepository.findByBookingIDs(booking);
        newPayMent.setBooking(bookingid); 

        Selling sellingid = sellingRepository.findBySellingIDs(selling);
        newPayMent.setSelling(sellingid); 

        Lease leaseid = leaseRepository.findByLeaseIDs(lease);
        newPayMent.setLease(leaseid); 

        return paymentRepository.save(newPayMent); 
    } 
    
        
}