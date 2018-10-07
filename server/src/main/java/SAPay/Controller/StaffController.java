package SAPay.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;
import java.util.stream.Collectors;
import SAPay.entity.Staff;
import  SAPay.Repository.StaffRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
@RestController
public class StaffController {
        private StaffRepository staffRepository;
        public StaffController(StaffRepository staffRepository) {
            this.staffRepository = staffRepository;
        }
        @RequestMapping("/staff")
        @CrossOrigin(origins = "http://localhost:4200")
        public Collection<Staff> staff() {
            return staffRepository.findAll().stream()
                    .filter(this::isStaff)
                    .collect(Collectors.toList());
        }
        private boolean isStaff(Staff staff){
            return staff.getStaffName().equals("Admin");
        }
}