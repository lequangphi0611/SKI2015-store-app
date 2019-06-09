import { Customer } from './../../model/Customer';
import { Invoice } from './../../model/Invoice';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {

  constructor(private http: HttpClient) { }

  getInvoicesByCustomer({id}: Customer): Observable<Invoice[]> {
    const fetchInvoiceByCustomerRequest = `/api/customers/${id}/invoices`;
    return this.http.get<Invoice[]>(fetchInvoiceByCustomerRequest);
  }

  insertInvoice(invoice: Invoice): Observable<Invoice> {
    const urlRequest = '/api/invoices';
    return this.http.post<Invoice>(urlRequest, invoice);
  }

  getInvoiceByCustomerIdAndInvoiceId(customerId: number, invoiceId: string): Observable<Invoice> {
    const urlRequest = `/api/customers/${customerId}/invoices/${invoiceId}`;
    return this.http.get<Invoice>(urlRequest);
  }
}
