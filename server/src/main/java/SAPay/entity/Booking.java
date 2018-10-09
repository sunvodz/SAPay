package SAPay.entity;
import javax.persistence.*;
import lombok.*;
import java.util.*;
import java.util.Collection;

@Data
@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Table(name="Booking")
public class Booking {
    @Id
    @SequenceGenerator(name="booking_seq",sequenceName="booking_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="booking_seq")
    @Column(name="Booking_ID",unique = true, nullable = false)
    private @NonNull Long bkId;
    private String bookingIDs;
    private Date date;
    private String location;
    private String statusBooking;
    private int bookPrice;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Customer.class)
    @JoinColumn(name = "customerId", insertable = true)
    private Customer customer;

    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Staff.class)
    @JoinColumn(name = "staffId", insertable = true)
    public Staff staff;

}