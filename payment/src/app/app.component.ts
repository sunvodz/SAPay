import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { PaymentService } from './payment.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Renting and Dressing System';
  staffs: Array<any>;

   constructor(private payService: PaymentService ) { }

  ngOnInit() {
  this.payService.getStaff().subscribe(data => {
      this.staffs = data;
      console.log(this.staffs);
    });
}
}
