import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort} from '@angular/material';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PaymentService } from '../payment.service';
import { getLocaleEraNames } from '@angular/common';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']

})
export class PaymentComponent implements OnInit {

  leaseColumns: string[];
  sellingColumns: string[];
  bookingColumns: string[];

  CurrentDate = new Date();
  idusers: Array<any>;
  payments: Array<any>;
  table;
  type;
  userid = 'Select ID User';
  selected = 'Select';

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
    this.payService.getPayment().subscribe(data => {
      this.payments = data;
      console.log(this.payments);
    });
  }
  searchId() {
    if (this.selected === 'Lease') {
      this.type = 'lease';
      this.table = ['CustomerId' , 'customerName'  , 'Name' , 'Price' , 'status'];
      console.log(this.userid , this.type)  ;
    } else if (this.selected === 'Selling') {
      this.type = 'Selling';
      this.table = [ 'CustomerId' , 'customerName'  , 'Name' , 'Price' , 'status'];
      console.log(this.userid , this.type);
    } else if (this.selected === 'Mack up and hair style') {
      this.type = 'Mack up and hair style';
      this.table = [ 'CustomerId' , 'customerName'  , 'Name' , 'Price' , 'status'];
      console.log(this.userid , this.type);
    } else if (this.userid === 'Select ID User') {
      alert('กรุณาเลือก Iduser');
    } else {
      alert('กรุณาเลือก Order');
    }
  }

  paymentButtom() {
    if (this.selected === 'Lease') {
      alert('จ่ายการจองสำเร็จ');
      console.log(this.userid , this.type) ;
    } else if (this.selected === 'Selling') {
      alert('จ่ายการซื้อสำเร็จ');
      console.log(this.userid , this.type);
    } else if (this.selected === 'Mack up and hair style') {
      alert('จ่ายการจองบริการแต่งหน้าสำเร็จ');
      console.log(this.userid , this.type);
    } else if (this.userid === 'Select ID User') {
      alert('กรุณาเลือก Iduser');
    } else {
      alert('กรุณาเลือก Order');
    }
  }

}
