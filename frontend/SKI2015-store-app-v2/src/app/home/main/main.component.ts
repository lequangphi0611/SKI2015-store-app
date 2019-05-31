import { Observable } from 'rxjs';
import { Title } from '@angular/platform-browser';
import { Product } from './../../model/Product';
import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/service/product/product.service';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  products: Observable<Product[]>;

  constructor(private productService: ProductService,
              private title: Title) { }

  ngOnInit() {
    this.title.setTitle("Store");
    this.fetchProducts();
  }

  fetchProducts(): void {
    this.products = this.productService.getProducts();
  }

}
