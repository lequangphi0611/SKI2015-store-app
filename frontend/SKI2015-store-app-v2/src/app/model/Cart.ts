import { CartItem } from './CartItem';

export class Cart {

  constructor(private _data: CartItem[]) {
  }


  public get data(): CartItem[] {
    return this._data;
  }

  public set data(v: CartItem[]) {
    this._data = v;
  }

  total(): number {
    let result = 0;
    this._data.forEach(item => {
      result += (item.product.price * item.quantity);
    });
    return result;
  }

  isExist({product}: CartItem): boolean {
    for (const item of this._data) {
      if (item.product.id === product.id) {
        return true;
      }
    }
    return false;
  }

  indexOf({product}: CartItem): number {
    return this._data.findIndex(
      item => item.product.id === product.id
    );
  }

  private addCartItem({product, quantity }: CartItem): void {
    this._data.push({product, quantity });
  }

  changeQuantity(index: number, quantity: number): CartItem {
    if (quantity < 1) {
      this._data.splice(index, 1);
      return null;
    }

    const newItem = {
      product : this._data[index].product,
      quantity
    };
    this._data[index] = newItem;
    return newItem;
  }

  private updateQuantityCartItem({product, quantity}: CartItem) {
    const index = this.indexOf({product});
    const quantityUpdated = this._data[index].quantity + quantity;
    this.changeQuantity(index, quantityUpdated);
  }

  push({product, quantity}: CartItem): Cart {
    if (this.isExist({product, quantity})) {
      this.updateQuantityCartItem({product, quantity});
    } else {
      this.addCartItem({product, quantity});
    }
    return this;
  }

  update({product, quantity}: CartItem): CartItem[] {
    const index = this.indexOf({product});
    this.changeQuantity(index, quantity);
    return this._data;
  }

  remove({ product }: CartItem) {
    this._data.splice(this.indexOf({product}), 1);
  }

}
