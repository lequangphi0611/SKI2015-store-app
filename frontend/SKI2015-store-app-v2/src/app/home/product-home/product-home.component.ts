import { Title } from '@angular/platform-browser';
import { Subscription, fromEvent } from 'rxjs';
import { CategoryService } from './../../service/category.service';
import { Category } from './../../model/Category';
import { ProductService } from './../../service/product/product.service';
import { Observable } from 'rxjs';
import { Product } from './../../model/Product';
import { Component, OnInit, OnDestroy, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import {debounceTime, distinctUntilChanged, map} from 'rxjs/operators';


@Component({
  selector: 'app-product-home',
  templateUrl: './product-home.component.html',
  styleUrls: ['./product-home.component.css']
})
export class ProductHomeComponent implements OnInit, AfterViewInit, OnDestroy {

  @ViewChild('filter') filter: ElementRef;

  products: Observable<Product[]>;

  categoriesView: { category: Category, countProducts: number }[] = [];

  categorySelected: Category = null;

  searchKey: string;

  onSearchSubscription: Subscription;


  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private title: Title) { }

  ngOnInit() {
    this.fetchProductsObservable();
    this.fetchCategoriesObservable();
    this.title.setTitle('Product');
  }

  ngAfterViewInit(): void {
    this.onSearchSubscription = fromEvent(this.filter.nativeElement, 'input')
      .pipe(
        debounceTime(800),
        map((event: Event) => (event.target as HTMLInputElement).value),
        distinctUntilChanged()
      )
      .subscribe((searchKey) => {
        this.searchKey = searchKey;
        this.fetchProductsObservable();
      });
  }

  fetchProductsObservable(): void {
    if (!this.categorySelected) {
      this.products = this.productService.getProducts(this.searchKey);
      return;
    }
    this.products = this.categoryService.getAllproductsById(this.categorySelected.id, this.searchKey);
  }

  async fetchCategoriesObservable() {
    const categories = await this.categoryService.getCategories().toPromise();
    categories.forEach(async (category) => {
      const products = await this.categoryService.getAllproductsById(category.id).toPromise();
      this.categoriesView.push({ category, countProducts: products.length });
    });
  }

  onSelectCategoryOption(): void {
    this.fetchProductsObservable();
  }

  ngOnDestroy(): void {
    this.onSearchSubscription.unsubscribe();
  }

}
