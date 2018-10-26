package SAPay;
import SAPay.Repository.*;
import SAPay.entity.*;
import java.util.Date;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
public class Data {
    Date date = new Date();
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Data.class, args);
    }
    public Data(BookingRepository bookingRepository,
                CustomerRepository customerRepository,
                LeaseRepository leaseRepository,
                PayMentRepository payMentRepository,
                SellingRepository sellingRepository,
                StaffRepository staffRepository){
    }
    
    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Bean
    ApplicationRunner init(BookingRepository bookingRepository,
                           CustomerRepository customerRepository,
                           LeaseRepository leaseRepository,
                           PayMentRepository payMentRepository,
                           SellingRepository sellingRepository,
                           StaffRepository staffRepository) {
        
        return args -> {
            Stream.of("Sunvo", "Ploy", "Ao").forEach(cusName -> {

                Customer customerName = new Customer();
                customerName.setCustomerName(cusName);
                customerRepository.save(customerName);
                
             if (cusName == "Sunvo") {
                Customer cusid = customerRepository.findByCusId(1L);
                customerName.setCustomerID("C"+cusid.getCusId());

                customerRepository.save(customerName);

             }
             else if(cusName == "Ploy"){
                Customer cusid = customerRepository.findByCusId(2L);
                customerName.setCustomerID("C"+cusid.getCusId());
                customerRepository.save(customerName);
             }
             else if(cusName == "Ao"){
                Customer cusid = customerRepository.findByCusId(3L);
                customerName.setCustomerID("C"+cusid.getCusId());
                customerRepository.save(customerName);
            }
            });

            Stream.of("Admin").forEach(stName -> {
                Staff staffdb = new Staff();
                staffdb.setStaffName(stName);
                staffRepository.save(staffdb);

                if (stName == "Admin") {
                    Staff staffid = staffRepository.findByStID(1L);
                    staffdb.setStaffIDs("S"+staffid.getStID());
                    staffdb.setPosition("administrator");
                    staffRepository.save(staffdb);
                 }
            });

                Customer c1 = customerRepository.findByCusId(1L);
                Customer c2 = customerRepository.findByCusId(2L);
                Customer c3 = customerRepository.findByCusId(3L);
                Staff st1 = staffRepository.findByStID(1L);
                


                Stream.of(1L,2L,3L).forEach(bkid -> {

                    Booking bookingdb = new Booking();
                    bookingdb.setBkId(bkid);
                    bookingRepository.save(bookingdb);
                    
                 if (bkid == 1L) {
                    bookingdb.setBookPrice(300);
                    bookingdb.setBookingDate(date);
                    bookingdb.setLocation("Suranaree");
                    bookingdb.setCustomer(c1);
                    bookingdb.setStatusBooking("not paid");
                    bookingdb.setStaff(st1);
                    bookingRepository.save(bookingdb);
    
                 }
                 else if(bkid == 2L){
                    bookingdb.setBookPrice(500);
                    bookingdb.setBookingDate(date);
                    bookingdb.setLocation("KORAT");
                    bookingdb.setCustomer(c2);
                    bookingdb.setStatusBooking("not paid");
                    bookingdb.setStaff(st1);
                    bookingRepository.save(bookingdb);
                 }
                 else if(bkid == 3L){
                    bookingdb.setBookPrice(1000);
                    bookingdb.setBookingDate(date);
                    bookingdb.setLocation("Bangkok");
                    bookingdb.setCustomer(c3);
                    bookingdb.setStatusBooking("not paid");
                    bookingdb.setStaff(st1);
                    bookingRepository.save(bookingdb);
                }
                });

                Stream.of(1L,2L,3L).forEach(leid -> {
                    Lease leasedb = new Lease();
                    leasedb.setLid(leid);
                    leaseRepository.save(leasedb);
                    
                 if (leid == 1L) {
                    leasedb.setLeaseName("Thai");
                    leasedb.setLeasePrice(500);
                    leasedb.setCustomer(c1);
                    leasedb.setDateStart(date);
                    leasedb.setDateEnd(date);
                    leasedb.setStatusLease("paid");
                    leaseRepository.save(leasedb);
                 }
                 else if(leid == 2L){
                    leasedb.setLeaseName("Lao");
                    leasedb.setLeasePrice(300);
                    leasedb.setCustomer(c2);
                    leasedb.setDateStart(date);
                    leasedb.setDateEnd(date);
                    leasedb.setStatusLease("not paid");
                    leaseRepository.save(leasedb);
                 }
                 else if(leid == 3L){
                    leasedb.setLeaseName("Eng");
                    leasedb.setLeasePrice(200);
                    leasedb.setCustomer(c3);
                    leasedb.setDateStart(date);
                    leasedb.setDateEnd(date);
                    leasedb.setStatusLease("not paid");
                    leaseRepository.save(leasedb);
                }
                });
                Stream.of(1L,2L).forEach(slid -> {
                    Selling sellingdb = new Selling();
                    sellingdb.setSeid(slid);
                    sellingRepository.save(sellingdb);

                 if (slid == 1L) {
                    sellingdb.setSellingName("Lao");
                    sellingdb.setSellingPrice(1500);
                    sellingdb.setCustomer(c1);
                    sellingdb.setStatusSelling("paid");
                    sellingRepository.save(sellingdb);
                 }
                 
                 else if(slid == 2L){
                    sellingdb.setSellingName("Lao");
                    sellingdb.setSellingPrice(1500);
                    sellingdb.setCustomer(c3);
                    sellingdb.setStatusSelling("not paid");
                    sellingRepository.save(sellingdb);
                }
                });

                Stream.of(1L).forEach(pmid -> {
                    PayMent paymentdb = new PayMent();
                    paymentdb.setPmId(pmid);
                    payMentRepository.save(paymentdb);
                    
                 if (pmid == 1L) {
                    paymentdb.setTypePay("Lease");
                    paymentdb.setCustomer(c1);
                    paymentdb.setDatePay(date);
                    paymentdb.setStatusPay("paid");
                    payMentRepository.save(paymentdb);
                 }
                });

                System.out.println("\n Spring-Boot Complete");
        };
        
    }
}