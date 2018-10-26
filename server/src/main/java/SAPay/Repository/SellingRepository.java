package SAPay.Repository;
import SAPay.entity.*;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface SellingRepository extends JpaRepository<Selling, Long> {
    Selling findBySeid(Long id);

    Optional<Selling> findByCustomer(Customer cuntomerID);
	Optional<Selling> findByStatusSelling(String status);
}