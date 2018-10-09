package SAPay.Repository;

import SAPay.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface BookingRepository extends JpaRepository<Booking, Long>{
    Booking findByBkId(Long id);
    Booking findByBookingIDs(String bookingIDs);
}