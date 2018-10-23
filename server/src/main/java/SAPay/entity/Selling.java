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
@Table(name = "Selling")
public class Selling {
    @Id
    @SequenceGenerator(name="selling_seq",sequenceName="selling_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="selling_seq")
    @Column(name="Selling_ID",unique = true, nullable = false)
    private @NonNull Long seid;
    private String sellingIDs;
    private String sellingName;
    private int    sellingPrice;
    private String statusSelling;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customerId", insertable = true)
    private Customer customer;


}