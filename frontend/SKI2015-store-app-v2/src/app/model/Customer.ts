import { Person } from 'src/app/model/Person';
export interface Customer extends Person {

  email: string;

  address: string;

  phoneNumber: string;

}
