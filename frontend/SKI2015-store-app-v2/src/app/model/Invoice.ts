import { DetailedInvoice } from './DetailedInvoice';
import { Customer } from './Customer';

export class Invoice {

  constructor(
    public id?: string,
    public paymentDate?: Date,
    public customer?: Customer,
    public detailedInvoices?: DetailedInvoice[]
  ) {}

}
