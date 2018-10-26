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
@Table(name="PayMent")
public class PayMent {
    @Id
    @SequenceGenerator(name="payMent_seq",sequenceName="payMent_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payMent_seq")
    @Column(name="PayMent_ID",unique = true, nullable = false)
    private @NonNull Long pmId;
    private @NonNull Date datePay;
    private String typePay;
    private String statusPay;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bkId")
    private Booking bkId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lid")
    private Lease lid;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seid")
    private Selling seid;

}