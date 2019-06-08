import { Component, OnDestroy, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { mergeMap } from 'rxjs/operators';
import { ProductService } from 'src/app/service/product/product.service';
import { Product } from './../../model/Product';
import { CartDataService } from './../../service/cart-data/cart-data.service';
import { NgModel } from '@angular/forms';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit, OnDestroy {

  product: Product;

  subscription: Subscription;

  cartSubscription: Subscription;

  constructor(
    private route: ActivatedRoute,
    private productService: ProductService,
    private title: Title,
    private cartDataService: CartDataService
  ) {
   }

  ngOnInit() {
    this.fetchProduct();
  }

  fetchProduct() {
    this.subscription = this.route.params
      .pipe(
        mergeMap((param) =>  this.productService.getProduct(param.id)),
      )
      .subscribe((product) => {
        this.title.setTitle(product.name);
        this.product = product;
      });
  }

  addToCart(quantityInput: NgModel) {
    this.cartDataService.addItem({product: this.product, quantity: +quantityInput.value});
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
