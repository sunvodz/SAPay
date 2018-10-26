package SAPay.Repository;

import SAPay.entity.Customer;
import SAPay.entity.Lease;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface LeaseRepository extends JpaRepository<Lease, Long>{
    Lease findByLid(Long id);
    Lease findByStatusLease(String statuslease);
	Optional<Lease> findByCustomer(Customer customerid);
}