import { CartItem } from './../../model/CartItem';
import { Cart } from './../../model/Cart';
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartDataService {

  readonly KEY_SESSION = 'cart';

  private _session = sessionStorage;

  private _cartData = new BehaviorSubject<Cart>(new Cart([]));

  cartData$ = this._cartData.asObservable();

  constructor() {
    this.create();
    this.fetch();
   }

  create(): Cart {
    let cartData = new Cart(this.getDataCart());
    if (!cartData.data) {
      cartData = new Cart([]);
      this.setItem(cartData);
    }
    return cartData;
  }

  setItem({data}: Cart) {
    this._session.setItem(this.KEY_SESSION, JSON.stringify(data));
    return this;
  }

  destroy(): CartDataService {
    this._cartData.next(new Cart([]));
    this._session.removeItem(this.KEY_SESSION);
    return this;
  }

  fetch(): CartDataService {
    this._cartData.next(new Cart(this.getDataCart()));
    return this;
  }

  getDataCart(): CartItem[] {
    return JSON.parse(this._session.getItem(this.KEY_SESSION));
  }

  addItem({product, quantity}: CartItem): CartDataService {
    const cartData = this.create();
    cartData.push({product, quantity});
    this.setItem(cartData)
        .fetch();
    return this;
  }

  updateItem({product, quantity}: CartItem): CartDataService {
    const cartData = this.create();
    cartData.update({product, quantity});
    this.setItem(cartData).fetch();
    return this;
  }
}
