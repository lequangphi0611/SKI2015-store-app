import { Customer } from './../../model/Customer';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  readonly URL_REQUEST = '/api/customers';

  constructor(private http: HttpClient) { }

  findCustomer(email: string): Observable<Customer> {
    return this.http.get<Customer>(`${this.URL_REQUEST}/${email}`);
  }

  insertCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.URL_REQUEST, customer);
  }

  existsEmail(email: string): Observable<boolean> {
    return new Observable<boolean>(obs => {
      this.findCustomer(email)
        .subscribe({
          next :  () => {
            obs.next(true);
          },
          error : () => {
            obs.next(false);
          }
        });
    });
  }

}
