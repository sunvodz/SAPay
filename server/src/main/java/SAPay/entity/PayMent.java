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
    private @NonNull String paymentIDs;
    private @NonNull Date datePay;
    private @NonNull String statusPay;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "staffId")
    private Staff staff;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bookingId")
    private Booking booking;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "leaseId")
    private Lease lease;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellingId")
    private Selling selling;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customerId")
    private Customer customer;




}