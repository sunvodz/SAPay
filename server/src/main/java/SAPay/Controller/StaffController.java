package SAPay.Controller;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import SAPay.entity.*;
import SAPay.Repository.*;
import java.util.*;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class StaffController {
        
        @Autowired  
        private StaffRepository staffRepository;
        public StaffController(StaffRepository staffRepository) {
            this.staffRepository = staffRepository;
        }
        @GetMapping("/staff")
        public Collection<Staff> staff() {
            return staffRepository.findAll().stream()
                    .filter(this::isStaff)
                    .collect(Collectors.toList());
        }
            private boolean isStaff(Staff staff){
                return staff.getStaffName().equals("Admin");
        }
}