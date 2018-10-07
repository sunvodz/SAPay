import {Component, OnInit, ViewChild} from '@angular/core';
import {MatSort, MatTableDataSource} from '@angular/material';
import {FormControl} from '@angular/forms';
import {Observable} from 'rxjs';
import {map, startWith} from 'rxjs/operators';
import { PaymentService } from '../payment.service';
export interface PeriodicElement {
  name: string;
  position: number;
  price: number;
  status: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name:  'AAA'  , price: 50, status: 'not paid'},
  {position: 2, name: 'BBB' , price: 50, status: 'not paid'},
  {position: 3, name: 'CCC' , price: 50, status: 'not paid'},
  {position: 4, name: 'DDD' , price: 50, status: 'not paid'},
];
const ELEMENT_DATAM: PeriodicElement[] = [
  {position: 1, name: 'AAAAA' , price: 50, status: 'not paid'},
  {position: 2, name: 'BBBBB' , price: 50, status: 'not paid'},
  {position: 3, name: 'CCCCC' , price: 50, status: 'not paid'},
];

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']

})
export class PaymentComponent implements OnInit {
  CurrentDate = new Date();
  idusers: Array<any>;
  displayedColumns: string[] = ['position', 'name', 'price' , 'status'];
  dataSource = new MatTableDataSource(ELEMENT_DATA);
  dataSourcem = new MatTableDataSource(ELEMENT_DATAM);
  selected = 'Select';
  selected1 = 'Select';
  idUser = 'SelectUser';
  myControl = new FormControl();
  filteredOptions: Observable<string[]>;


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
    this.dataSource.sort = this.sort;
    this.dataSourcem.sort = this.sort;
  }


}
