import { map, mergeMap } from 'rxjs/operators';
import { Observable, Subscription } from 'rxjs';
import { Title } from '@angular/platform-browser';
import { ProductService } from 'src/app/service/product/product.service';
import { Product } from './../../model/Product';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit, OnDestroy {

  product: Product;

  subscription: Subscription;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private title: Title
  ) {
   }

  ngOnInit() {
    this.fetchProduct();
  }

  fetchProduct() {
    this.subscription = this.route.params
      .pipe(
        mergeMap((param) =>  this.productService.getProduct(param.id))
      )
      .subscribe((product) => {
        this.title.setTitle(product.name);
        this.product = product;
      });

  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
