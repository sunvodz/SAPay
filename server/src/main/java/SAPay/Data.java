package SAPay;
import SAPay.Repository.*;
import SAPay.entity.*;

import java.awt.print.Book;
import java.util.Date;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.ApplicationArguments;
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

            Stream.of("Admin", "SunvoDz", "Au").forEach(stName -> {
                Staff staffdb = new Staff();
                staffdb.setStaffName(stName);
                staffRepository.save(staffdb);

                if (stName == "Admin") {
                    Staff staffid = staffRepository.findByStID(1L);
                    staffdb.setStaffIDs("S"+staffid.getStID());
                    staffdb.setPosition("administrator");
                    staffRepository.save(staffdb);
                 }
                 else if(stName == "SunvoDz"){
                    Staff staffid = staffRepository.findByStID(2L);
                    staffdb.setStaffIDs("S"+staffid.getStID());
                    staffdb.setPosition("accountant");
                    staffRepository.save(staffdb);
                 }
                 else if(stName == "Au"){
                    Staff staffid = staffRepository.findByStID(3L);
                    staffdb.setStaffIDs("S"+staffid.getStID());
                    staffdb.setPosition("accountant");
                    staffRepository.save(staffdb);

                }
            });
                Customer c1 = customerRepository.findByCusId(1L);
                Customer c2 = customerRepository.findByCusId(2L);
                Customer c3 = customerRepository.findByCusId(3L);
                Staff st1 = staffRepository.findByStID(1L);
                Staff st2 = staffRepository.findByStID(2L);
                Staff st3 = staffRepository.findByStID(3L);

                Stream.of("B001","B002","B003").forEach(bkid -> {

                    Booking bookingdb = new Booking();
                    bookingdb.setBookingIDs(bkid);
                    bookingRepository.save(bookingdb);
                    
                 if (bkid == "B001") {
                    bookingdb.setBookPrice(300);
                    bookingdb.setDate(date);
                    bookingdb.setLocation("Suranaree");
                    bookingdb.setCustomer(c1);
                    bookingdb.setStatusBooking("not paid");
                    bookingdb.setStaff(st1);
                    bookingRepository.save(bookingdb);
    
                 }
                 else if(bkid == "B002"){
                    bookingdb.setBookPrice(500);
                    bookingdb.setDate(date);
                    bookingdb.setLocation("KORAT");
                    bookingdb.setCustomer(c2);
                    bookingdb.setStatusBooking("not paid");
                    bookingdb.setStaff(st2);
                    bookingRepository.save(bookingdb);
                 }
                 else if(bkid == "B003"){
                    bookingdb.setBookPrice(1000);
                    bookingdb.setDate(date);
                    bookingdb.setLocation("Bangkok");
                    bookingdb.setCustomer(c3);
                    bookingdb.setStatusBooking("not paid");
                    bookingdb.setStaff(st3);
                    bookingRepository.save(bookingdb);
                }
                });

                Stream.of("L001","L002","L003").forEach(leid -> {
                    Lease leasedb = new Lease();
                    leasedb.setLeaseIDs(leid);
                    leaseRepository.save(leasedb);
                    
                 if (leid == "L001") {
                    leasedb.setLeaseName("Thai");
                    leasedb.setLeasePrice(500);
                    leasedb.setCustomer(c1);
                    leasedb.setDateStart(date);
                    leasedb.setDateEnd(date);
                    leasedb.setStatusLease("not paid");
                    leaseRepository.save(leasedb);
                 }
                 else if(leid == "L002"){
                    leasedb.setLeaseName("Lao");
                    leasedb.setLeasePrice(300);
                    leasedb.setCustomer(c2);
                    leasedb.setDateStart(date);
                    leasedb.setDateEnd(date);
                    leasedb.setStatusLease("not paid");
                    leaseRepository.save(leasedb);
                 }
                 else if(leid == "L003"){
                    leasedb.setLeaseName("Eng");
                    leasedb.setLeasePrice(200);
                    leasedb.setCustomer(c3);
                    leasedb.setDateStart(date);
                    leasedb.setDateEnd(date);
                    leasedb.setStatusLease("not paid");
                    leaseRepository.save(leasedb);
                }
                });
                Stream.of("SL001","SL003").forEach(slid -> {
                    Selling sellingdb = new Selling();
                    sellingdb.setSellingIDs(slid);
                    sellingRepository.save(sellingdb);

                 if (slid == "SL001") {
                    sellingdb.setSellingName("Lao");
                    sellingdb.setSellingPrice(1500);
                    sellingdb.setCustomer(c1);
                    sellingdb.setStatusSelling("not paid");
                    sellingRepository.save(sellingdb);
                 }
                 
                 else if(slid == "SL003"){
                    sellingdb.setSellingName("Lao");
                    sellingdb.setSellingPrice(1500);
                    sellingdb.setCustomer(c3);
                    sellingdb.setStatusSelling("not paid");
                    sellingRepository.save(sellingdb);
                }
                });
                Booking bk1 = bookingRepository.findByBkId(1L);
                Booking bk2 = bookingRepository.findByBkId(2L);
                Booking bk3 = bookingRepository.findByBkId(3L);
                Lease le1 = leaseRepository.findByLid(1L);
                Lease le2 = leaseRepository.findByLid(2L);
                Lease le3 = leaseRepository.findByLid(3L);
                Selling sl1 = sellingRepository.findBySeid(1L);
                Selling sl2 = sellingRepository.findBySeid(2L);
                Selling sl3 = sellingRepository.findBySeid(3L);

                Stream.of("P001","P002","P003").forEach(pmid -> {
                    PayMent paymentdb = new PayMent();
                    paymentdb.setPaymentIDs(pmid);
                    payMentRepository.save(paymentdb);
                    
                 if (pmid == "P001") {
                    paymentdb.setBooking(bk1);
                    paymentdb.setLease(le1);
                    paymentdb.setSelling(sl1);
                    paymentdb.setCustomer(c1);
                    paymentdb.setStaff(st1);
                    paymentdb.setDatePay(date);
                    paymentdb.setStatusPay("paid");
                    payMentRepository.save(paymentdb);
                 }
                 else if(pmid == "P002"){
                    paymentdb.setBooking(bk2);
                    paymentdb.setLease(le2);
                    paymentdb.setSelling(sl2);
                    paymentdb.setCustomer(c2);
                    paymentdb.setStaff(st2);
                    paymentdb.setDatePay(date);
                    paymentdb.setStatusPay("paid");
                    payMentRepository.save(paymentdb);
                    
                 }
                 else if(pmid == "P003"){
                    paymentdb.setBooking(bk3);
                    paymentdb.setLease(le3);
                    paymentdb.setSelling(sl3);
                    paymentdb.setCustomer(c3);
                    paymentdb.setStaff(st3);
                    paymentdb.setDatePay(date);
                    paymentdb.setStatusPay("paid");
                    payMentRepository.save(paymentdb);
                    
                }
                });
            
                System.out.println("\n Spring-Boot Complete");
        };
        
    }
}