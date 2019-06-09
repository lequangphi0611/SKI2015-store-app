import { DetailedInvoice } from './../../model/DetailedInvoice';
import { Customer } from './../../model/Customer';
import { UserInfoStorageService } from './../../service/user-info/user-info-storage.service';
import { Invoice } from './../../model/Invoice';
import { Observable } from 'rxjs';
import { Subscription } from 'rxjs';
import { InvoiceService } from './../../service/Invoice/invoice.service';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { map, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.css'],
})
export class ConfirmationComponent implements OnInit, OnDestroy {
  subcription: Subscription;

  invoice: Invoice;

  constructor(
    private route: ActivatedRoute,
    private invoiceService: InvoiceService,
    private userInfoStorageService: UserInfoStorageService
  ) {}

  ngOnInit() {
    this.subcription = this.route.params
      .pipe(
        map(params => params.invoiceId),
        switchMap(invoiceId => this.findInvoice(invoiceId))
      )
      .subscribe(invoice => {
        this.invoice = invoice;
        console.log(invoice);
      });
  }

  findInvoice(invoiceId: string): Observable<Invoice> {
    return this.userInfoStorageService.user$.pipe(
      map(user => (user.person as Customer).id),
      switchMap(customerId =>
        this.invoiceService.getInvoiceByCustomerIdAndInvoiceId(
          customerId,
          invoiceId
        )
      )
    );
  }

  total(detailedInvoicesParams: DetailedInvoice[]): number {
    const detailedInvoices = detailedInvoicesParams || [];
    let total = 0;
    detailedInvoices.forEach(
      currentValue => (total += currentValue.price * currentValue.quantity)
    );
    return total;
  }

  ngOnDestroy(): void {
    this.subcription.unsubscribe();
  }
}
