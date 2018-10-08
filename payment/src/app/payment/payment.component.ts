import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { PaymentService } from '../payment.service';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']

})
export class PaymentComponent implements OnInit {

  leaseColumns: string[] = ['no' , 'customerName' , 'leaseName' , 'leasePrice' , 'statusLease'];

  CurrentDate = new Date();
  idusers: Array<any>;
  payments: Array<any>;
  userName = 'Select Id User';
  selected = 'Select';
  selected1 = 'Select';
  myControl = new FormControl();
  filteredIdusers: Observable<string[]>;


  @ViewChild(MatSort)
  sort: MatSort;
  constructor(private payService: PaymentService ) { }

  ngOnInit() {

    this.payService.getCustomer().subscribe(data => {
      this.idusers = data;
      console.log(this.idusers);
    });
    this.payService.getCustomer().subscribe(data => {
      this.idusers = data;
    });
    this.payService.getPayment().subscribe(data => {
      this.payments = data;
      console.log(this.payments);
    });

    this.payService.getPayment().subscribe(data => {
      this.payments = data;
    });
  }
  searchId(){}
  searchLease(){}
  searchSelling(){}
  paymentButtom(){}
}
