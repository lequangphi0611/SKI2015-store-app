import { Product } from './Product';
import { Invoice } from './Invoice';

export class DetailedInvoice {

  constructor(
    public id?: number,
    public product?: Product,
    public price?: number,
    public quantity?: number
    ) {}

}
