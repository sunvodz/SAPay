import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class PaymentService {

  constructor(private http: HttpClient) {}

getStaff(): Observable<any> {
    return this.http.get('//localhost:8080/staff');
}
getCustomer(): Observable<any> {
    return this.http.get('//localhost:8080/customer');
}
 getPayment(): Observable<any> {
   return this.http.get('//localhost:8080/payment');
 }
getSelling(cusid): Observable<any> {
  return this.http.get('//localhost:8080/selling/' + cusid);
}
getBooking(cusid): Observable<any> {
  return this.http.get('//localhost:8080/booking/' + cusid);
}

getLease(cusid): Observable<any> {
  return this.http.get('//locahost:8080/lease/' + cusid );
}

}


