package SAPay.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.stream.Collectors;
import SAPay.entity.PayMent;
import SAPay.Repository.PayMentRepository;

@RestController

@CrossOrigin(origins = "http://localhost:4200")
public class PayMentController {

        private PayMentRepository paymentRepository;
        public PayMentController(PayMentRepository paymentRepository) {
            this.paymentRepository = paymentRepository;
        }
        @RequestMapping("/payment")
        public Collection<PayMent> payment() {
            return paymentRepository.findAll().stream()
                    .collect(Collectors.toList());
    }
        @GetMapping(path = {"/payment/{paymentIDs}"})
        public PayMent findOne(@PathVariable("paymentIDs") String paymentIDs){
            return paymentRepository.findByPaymentIDs(paymentIDs);
    }
}