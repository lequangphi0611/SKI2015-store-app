
import { ProductHomeComponent } from './home/product-home/product-home.component';
import { ProductDetailComponent } from './home/product-detail/product-detail.component';
import { UserComponent } from './dashboard/user/user.component';
import { CategoryDashboardComponent } from './dashboard/category-dashboard/category-dashboard.component';
import { ProductComponent } from './dashboard/product/product.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { RegisterComponent } from './home/register/register.component';
import { LoginComponent } from './home/login/login.component';
import { MainComponent } from './home/main/main.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path : 'home',
    component : HomeComponent,
    children : [
      {
        path : 'index',
        component : MainComponent
      },
      {
        path : 'login',
        component : LoginComponent
      },
      {
        path : 'register',
        component : RegisterComponent
      },
      {
        path : 'products',
        component : ProductHomeComponent
      },
      {
        path : 'product/:id',
        component : ProductDetailComponent
      },
      {
        path : '',
        redirectTo : 'index',
        pathMatch : 'full'
      }
    ]
  },
  {
    path : 'dashboard',
    component  : DashboardComponent,
    children : [
      {
        path : 'product',
        component : ProductComponent
      },
      {
        path : 'category',
        component : CategoryDashboardComponent
      },
      {
        path : 'user',
        component : UserComponent
      },
      {
        path : '',
        redirectTo : 'product',
        pathMatch : 'full'
      }
    ]
  },
  {
    path : '',
    redirectTo : '/home/index',
    pathMatch : 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
