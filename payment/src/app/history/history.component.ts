import { Component, OnInit } from '@angular/core';
import { PaymentService } from '../payment.service';


@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  payment: Array<any>;

  displayedColumns: string[] = ['pmId', 'datePay', 'typePay', 'statusPay', 'customerName'];

  constructor(private paymentService: PaymentService) { }

  ngOnInit() {
    this.paymentService.getPayment().subscribe(data => {
      this.payment = data;
      console.log(this.payment);
    });
  }

}

