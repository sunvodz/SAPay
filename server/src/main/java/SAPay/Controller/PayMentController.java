package SAPay.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.stream.Collectors;
import SAPay.entity.PayMent;
import SAPay.Repository.PayMentRepository;

@RestController
public class PayMentController {
        private PayMentRepository paymentRepository;

        public PayMentController(PayMentRepository paymentRepository) {
            this.paymentRepository = paymentRepository;
        }
    @RequestMapping("/payMent")
    @CrossOrigin(origins = "http://localhost:4200")
    public Collection<PayMent> payMent() {
        return paymentRepository.findAll().stream()
                .collect(Collectors.toList());
    }
}