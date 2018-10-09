package SAPay.Repository;

import SAPay.entity.Lease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface LeaseRepository extends JpaRepository<Lease, Long>{
    Lease findByLid(Long id);
    Lease findByLeaseIDs(String leaseIDs);
    Lease findByStatusLease(String statuslease);
}