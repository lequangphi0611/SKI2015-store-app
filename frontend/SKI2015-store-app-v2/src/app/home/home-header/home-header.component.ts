import { CartDataService } from './../../service/cart-data/cart-data.service';
import { Router } from '@angular/router';
import { Customer } from './../../model/Customer';
import {
  UserInfoStorageService,
  UserInfo,
} from './../../service/user-info/user-info-storage.service';
import { Category } from 'src/app/model/Category';
import { Observable, Subscription, of } from 'rxjs';
import { CategoryService } from './../../service/category.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { switchMap, map } from 'rxjs/operators';

@Component({
  selector: 'app-home-header',
  templateUrl: './home-header.component.html',
  styleUrls: ['./home-header.component.css'],
})
export class HomeHeaderComponent implements OnInit {
  categories: Observable<Category[]>;

  user$: Observable<Customer>;

  cartItemNumber$: Observable<number>;

  constructor(
    private categoryService: CategoryService,
    private userInfoStorageService: UserInfoStorageService,
    private router: Router,
    private cartDataService: CartDataService
  ) {}

  ngOnInit() {
    this.fetchCategories();
    this.user$ = this.userInfoStorageService.user$.pipe(
      map(userInfo => userInfo.person as Customer)
    );
    this.cartItemNumber$ = this.cartDataService.cartData$.pipe(
      map(cart => cart.data.length)
    );
  }

  fetchCategories() {
    this.categories = this.categoryService.getCategories();
  }

  logout() {
    this.userInfoStorageService.removeUser();
    this.router.navigate(['/login']);
  }

  goShopCart() {
    this.router.navigate(['/home/cart']);
  }
}
