import { Component, OnInit} from '@angular/core';
import { DataSource } from '@angular/cdk/collections';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { PaymentService } from '../payment.service';


export interface Lease {
  userID: string;
  LeaseName: string;
  LeasePrice: String;
  LeaseStatus: String;
}

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']

})

export class PaymentComponent implements OnInit {

  CurrentDate = new Date();
  idusers: Array<any>;
  leases: Array<any>;
  sellings: Array<any>;
  bookings: Array<any>;
  payments: Array<any>;
  selected = 'Select';
  userId: Array<any>;
  searchData = {
    userId : 'Select ID User',
  };

  ColumnLease: string[] = ['customerID', 'CustomerName', 'LeaseName' , 'LeasePrice', 'LeaseStatus'];

  // ColumnSelling: string[] = ['customerID', 'CustomerName', 'SellingName' , 'SellingPrice', 'SellingStatus'];
  // ColumnBooking: string[] = ['customerID', 'CustomerName', 'BookingName' , 'BookingPrice', 'BookingStatus'];


  constructor(private paymentService: PaymentService, private httpClient: HttpClient ) { }

  ngOnInit() {

    this.paymentService.getCustomer().subscribe(data => {
      this.idusers = data;
      console.log(this.idusers);
    });
    this.paymentService.getPayment().subscribe(data => {
      this.payments = data;
      console.log(this.payments);
    });
  }
  searchId() {

    this.httpClient.get('http://localhost:8080/lease/' + this.searchData.userId)
    .subscribe(
        data => {
          this.paymentService.getLease(this.searchData.userId);
            console.log('Search successful', data);
        },
        error => {
            console.log('Error', error);
        }
    );
    this.paymentService.getLease(this.searchData.userId).subscribe(data => {
      this.leases = data;
      console.log(this.leases);
    });
  }


  paymentButtom() {
    if (this.selected === 'lease') {
      alert('จ่ายการจองสำเร็จ');
      console.log(this.searchData.userId ) ;
    } else if (this.selected === 'selling') {
      alert('จ่ายการซื้อสำเร็จ');
      console.log(this.searchData.userId );
    } else if (this.selected === 'booking') {
      alert('จ่ายการจองบริการแต่งหน้าสำเร็จ');
      console.log(this.searchData.userId );
    } else if (this.searchData.userId === 'Select ID User') {
      alert('กรุณาเลือก Iduser');
    } else {
      alert('กรุณาเลือก Order');
    }
  }

}
