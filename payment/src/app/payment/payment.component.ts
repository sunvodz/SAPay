import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { PaymentService } from '../payment.service';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']

})

export class PaymentComponent implements OnInit {

  CurrentDate = new Date();
  lease: Array<any>;
  selling: Array<any>;
  booking: Array<any>;
  userId: Array<any>;


  LeaseName: Array<any>;
  LeasePrice: Array<any>;
  LeaseStatus: Array<any>;


  SellingName: Array<any>;
  SellingPrice: Array<any>;
  StatusSelling: Array<any>;

  Bookingid: Array<any>;
  BookingPrice: Array<any>;
  StatusBooking: Array<any>;

  userData = {
    userId : ''
  };

  leaseData = {
    LeaseName: '',
    LeasePrice: '',
    LeaseStatus: ''
  };

  sellingData = {
    SellingName: '',
    SellingPrice: '',
    StatusSelling: ''
  };

  bookingData = {
    Bookingid: '',
    BookingPrice: '',
    StatusBooking: ''
  };
  num = 1;
  lid = 0;
  sell = 0;
  book = 0;
  payment: Array<any>;


  constructor(private paymentService: PaymentService, private httpClient: HttpClient ) { }

  ngOnInit() {

    this.paymentService.getCustomer().subscribe(data => {
      this.userId = data;
      console.log(this.userId);
    });



  }
  searchId() {
    this.httpClient.get('http://localhost:8080/lease'),
    this.httpClient.get('http://localhost:8080/selling'),
    this.httpClient.get('http://localhost:8080/booking')
    .subscribe(
        data => {
          this.paymentService.getLease(), this.paymentService.getSelling();
            console.log('Search id successful', data);
        },
        error => {
            console.log('Error', error);
        }
    );
    this.paymentService.getLease().subscribe(data => {
      this.LeaseName = data;
      this.LeasePrice = data;
      this.LeaseStatus = data;
      console.log(this.LeaseName);
    });

    this.paymentService.getSelling().subscribe(data => {
      this.SellingName = data;
      this.SellingPrice = data;
      this.StatusSelling = data;
      console.log(this.SellingName);
    });

    this.paymentService.getBooking().subscribe(data => {
      this.Bookingid = data;
      this.BookingPrice = data;
      this.StatusBooking = data;
      console.log(this.Bookingid);
    });
  }
  payLeaseButtom() {
    if (this.leaseData.LeaseName === '' ||
    this.leaseData.LeasePrice ===  ''
    ) {
     alert('กรอกข้อมูลให้ครบถ้วน');
} else {
  this.num++;
  this.httpClient.post('http://localhost:8080/payment/'+ 'P00' + this.num + '/' + 'Lease' + '/' + 'paid' + '/' + this.userData.userId, this.payment)
  .subscribe(
    data => {
        console.log('Post successful', data);
        alert('สำเร็จ');
    },
    error => {
        console.log('Error', error);
    }
);
this.lid++;
this.httpClient.put('http://localhost:8080/lease/' + this.lid + '/' + this.leaseData.LeaseStatus, this.lease)
    .subscribe(
      data => {
        if (data) {
          console.log('Success');
        }
      },
      error => {
      console.log('error', error);
    }
    );

  }
}
  paySellingButtom() {
    if (this.sellingData.SellingName === '' ||
    this.sellingData.SellingPrice ===  ''
    ) {
     alert('กรอกข้อมูลให้ครบถ้วน');
} else {
  this.num++ ;
  this.httpClient.post('http://localhost:8080/payment/'+ 'S00' + this.num + '/' + 'Selling' + '/' + 'paid' + '/' + this.userData.userId, this.payment)
  .subscribe(
    data => {
        console.log('Post successful', data);
        alert('สำเร็จ');
    },
    error => {
        console.log('Error', error);
    }
);

  }
  this.sell++;
this.httpClient.put('http://localhost:8080/selling/' + this.sell + '/' + this.sellingData.StatusSelling, this.selling)
    .subscribe(
      data => {
        if (data) {
          console.log('Success');
        }
      },
      error => {
      console.log('error', error);
    }
    );
  }
  payBookingButtom() {

    if (this.bookingData.Bookingid === '' ||
  this.bookingData.BookingPrice ===  ''
  ) {
   alert('กรอกข้อมูลให้ครบถ้วน');
} else {
  this.num++ ;
this.httpClient.post('http://localhost:8080/payment/'+ 'B00' + this.num + '/' + 'Booking' + '/' + 'paid' + '/' + this.userData.userId, this.payment)
.subscribe(
  data => {
      console.log('Post successful', data);
      alert('สำเร็จ');
  },
  error => {
      console.log('Error', error);
  }
);

}
this.book++;
this.httpClient.put('http://localhost:8080/booking/' + this.book + '/' + this.bookingData.StatusBooking, this.booking)
    .subscribe(
      data => {
        if (data) {
          console.log('Success');
        }
      },
      error => {
      console.log('error', error);
    }
    );
}


}
