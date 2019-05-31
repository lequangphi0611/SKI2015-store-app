import { Observable } from 'rxjs';
import { Product } from './../../model/Product';
import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-show-product',
  templateUrl: './show-product.component.html',
  styleUrls: ['./show-product.component.css']
})
export class ShowProductComponent implements OnInit {

  @Input() products: Observable<Product[]>;

  constructor() { }

  ngOnInit() {
  }

}
