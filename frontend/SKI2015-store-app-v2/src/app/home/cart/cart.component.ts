import { Title } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Product } from './../../model/Product';
import { CartItem } from './../../model/CartItem';
import { Subscription } from 'rxjs';
import { CartDataService } from './../../service/cart-data/cart-data.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { map } from 'rxjs/operators';
import { Cart } from 'src/app/model/Cart';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnDestroy {

  cartDataSubcrition: Subscription;

  cart: Cart = new Cart([]);

  constructor(
    private cartDataService: CartDataService,
    private router: Router,
    private title: Title
  ) { }

  ngOnInit() {
    this.title.setTitle('Cart');
    this.cartDataSubcrition = this.cartDataService.cartData$
      .subscribe(cartItem => this.cart = cartItem);
  }

  ngOnDestroy(): void {
    this.cartDataSubcrition.unsubscribe();
  }

  updateCartItem(product: Product, quantity: number): void {
    this.cartDataService.addItem({product, quantity});
  }

  showDetail(productId: number) {
    this.router.navigate(['/home/product', productId]);
  }

  changeQuantity(product: Product, quantity: number) {
    console.log(product);
    this.cartDataService.updateItem({product, quantity: +quantity});
  }

}
