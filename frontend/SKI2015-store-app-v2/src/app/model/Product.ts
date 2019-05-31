import { Category } from './Category';


export class Product {

  constructor(
    public id?: number,
    public name?: string,
    public manufactureDate?: Date,
    public imagePath?: string,
    public price?: number,
    public description?: string,
    public category?: Category
  ) { }

}
