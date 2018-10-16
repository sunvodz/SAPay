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
            return payMent.getStatusPay().equals("paid") ;
        }
        
        @GetMapping("/selling")
        public Collection<Selling> selling() {
            return sellingRepository.findAll().stream()
                    .filter(this::isSelling)
                    .collect(Collectors.toList());
        }
        private boolean isSelling(Selling selling){
            return selling.getStatusSelling().equals("not paid") ;
        }

        @GetMapping("/lease")
        public Collection<Lease> lease() {
            return leaseRepository.findAll().stream()
                    .filter(this::isLease)
                    .collect(Collectors.toList());
        }
        private boolean isLease(Lease lease){
            return lease.getStatusLease().equals("not paid") ;
        }

        @GetMapping("/booking")
        public Collection<Booking> booking() {
            return bookingRepository.findAll().stream()
                    .filter(this::isBooking)
                    .collect(Collectors.toList());
        }
        private boolean isBooking(Booking booking){
            return booking.getStatusBooking().equals("not paid") ;
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
        @PostMapping("/payment/{payid}/{statuspay}/{staff}/{customer}/{booking}/{selling}/{lease}")
        public PayMent newPayMent(@PathVariable String payid,@PathVariable String statuspay,
                            @PathVariable  String staff,@PathVariable String customer,
                            @PathVariable String booking,@PathVariable  String selling,
                            @PathVariable  String lease){
        PayMent newPayMent = new PayMent();
        newPayMent.setStatusPay(statuspay);
        newPayMent.setPaymentIDs(payid);
        
        Date datePay = new Date();
        newPayMent.setDatePay(datePay);

        Staff staffid = staffRepository.findByStaffIDs(staff);
        newPayMent.setStaff(staffid);

        Customer customerid = customerRepository.findByCustomerID(customer);
        newPayMent.setCustomer(customerid);
        
        Booking bookingid = bookingRepository.findByBookingIDs(booking);
        newPayMent.setBooking(bookingid); 

        Selling sellingid = sellingRepository.findBySellingIDs(selling);
        newPayMent.setSelling(sellingid); 

        Lease leaseid = leaseRepository.findByLeaseIDs(lease);
        newPayMent.setLease(leaseid);

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


}