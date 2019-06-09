import { ConfirmationComponent } from './home/confirmation/confirmation.component';
import { CheckOutComponent } from './home/check-out/check-out.component';
import { CartComponent } from './home/cart/cart.component';
import { AdminGuard } from './auth/admin.guard';
import { AuthGuardGuard } from './auth/auth-guard.guard';
import { RegisterComponent } from './register/register.component';

import { LoginComponent } from './login/login.component';

import { ProductHomeComponent } from './home/product-home/product-home.component';
import { ProductDetailComponent } from './home/product-detail/product-detail.component';
import { UserComponent } from './dashboard/user/user.component';
import { CategoryDashboardComponent } from './dashboard/category-dashboard/category-dashboard.component';
import { ProductComponent } from './dashboard/product/product.component';
import { DashboardComponent } from './dashboard/dashboard.component';

import { MainComponent } from './home/main/main.component';
import { HomeComponent } from './home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  {
    path : 'home',
    component : HomeComponent,
    canActivate: [AuthGuardGuard],
    children : [
      {
        path : 'index',
        component : MainComponent
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
        path : 'cart',
        component : CartComponent
      },
      {
        path : 'check-out',
        component : CheckOutComponent
      },
      {
        path : 'confirmation/:invoiceId',
        component : ConfirmationComponent
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
    canActivate : [AuthGuardGuard, AdminGuard],
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
    path : 'login',
    component: LoginComponent
  },
  {
    path : 'register',
    component: RegisterComponent
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
