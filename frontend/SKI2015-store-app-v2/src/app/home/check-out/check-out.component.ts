import { InvoiceService } from './../../service/Invoice/invoice.service';
import { DetailedInvoice } from './../../model/DetailedInvoice';
import { Customer } from './../../model/Customer';
import { Invoice } from './../../model/Invoice';
import { UserInfoStorageService } from './../../service/user-info/user-info-storage.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { CartDataService } from './../../service/cart-data/cart-data.service';
import { Cart } from 'src/app/model/Cart';
import { Subscription } from 'rxjs';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { map, switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-check-out',
  templateUrl: './check-out.component.html',
  styleUrls: ['./check-out.component.css'],
})
export class CheckOutComponent implements OnInit, OnDestroy {
  cartSubcription: Subscription;

  cart: Cart = new Cart([]);

  proceedPaySubcription: Subscription;

  constructor(
    private cartDataService: CartDataService,
    private title: Title,
    private router: Router,
    private userInfoStorageService: UserInfoStorageService,
    private invoiceService: InvoiceService
  ) {}

  ngOnInit() {
    this.title.setTitle('Check Out');
    this.cartSubcription = this.cartDataService.cartData$.subscribe(cart => {
      this.cart = cart;
    });
  }

  proceedToPay() {
    this.proceedPaySubcription = this.userInfoStorageService.user$
      .pipe(
        map(user => {
          const invoice = new Invoice();
          invoice.customer = user.person as Customer;
          invoice.paymentDate = new Date();
          return invoice;
        }),
        map(invoice => {
          const cartItem = this.cart.data;
          const detailedInvoices: DetailedInvoice[] = [];
          cartItem.forEach(currentValue => {
            const detailedInvoice = new DetailedInvoice();
            detailedInvoice.product = currentValue.product;
            detailedInvoice.quantity = currentValue.quantity;
            detailedInvoice.price = currentValue.product.price;
            detailedInvoices.push(detailedInvoice);
          });
          invoice.detailedInvoices = detailedInvoices;
          return invoice;
        }),
        switchMap(invoice => {
          return this.invoiceService.insertInvoice(invoice);
        })
      )
      .subscribe(invoice => {
        this.cartDataService.destroy();
        this.router.navigate(['/home/confirmation', invoice.id]);
      });
  }

  ngOnDestroy(): void {
    this.cartSubcription.unsubscribe();
  }
}
