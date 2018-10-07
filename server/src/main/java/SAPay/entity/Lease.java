package SAPay.entity;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.util.*;
import java.util.Collection;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Lease")
public class Lease {
    @Id
    @SequenceGenerator(name="lease_seq",sequenceName="lease_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="lease_seq")
    @Column(name="Lease_ID",unique = true, nullable = false)
    private @NonNull Long lid;
    private @NonNull String leaseIDs;
    private @NonNull String leaseName;
    private @NonNull int    leasePrice;
    private @NonNull String statusLease;
    private @NonNull Date   dateStart;
    private @NonNull Date   dateEnd;
    
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customerId", insertable = true)
    private Customer customer;


}