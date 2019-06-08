import { Product } from './Product';

export class CartItem {

  constructor(
    public product?: Product,
    public quantity?: number
  ) {
    if (!quantity || quantity < 1) {
      this.quantity = 1;
    }
  }

}
