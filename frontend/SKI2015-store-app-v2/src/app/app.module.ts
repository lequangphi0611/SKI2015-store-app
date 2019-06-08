import { CartDataService } from './service/cart-data/cart-data.service';
import { LoginComponent } from './login/login.component';
import { UserInfoStorageService } from './service/user-info/user-info-storage.service';
import { CustomerService } from './service/customer/customer.service';
import { AdminService } from './service/admin/admin.service';
import { ProductService } from './service/product/product.service';
import { UploadImageService } from './service/upload-image/upload-image.service';
import { CategoryService } from './service/category.service';
import { BrowserModule, Title } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HomeHeaderComponent } from './home/home-header/home-header.component';
import { HomeFooterComponent } from './home/home-footer/home-footer.component';
import { MainComponent } from './home/main/main.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProductComponent } from './dashboard/product/product.component';
import { CategoryDashboardComponent } from './dashboard/category-dashboard/category-dashboard.component';
import { UserComponent } from './dashboard/user/user.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { NavbarComponent } from './dashboard/navbar/navbar.component';
import { SideBarComponent } from './dashboard/side-bar/side-bar.component';
import { ShowProductComponent } from './home/show-product/show-product.component';
import { ProductDetailComponent } from './home/product-detail/product-detail.component';
import { ProductHomeComponent } from './home/product-home/product-home.component';
import { ImageChooseComponent } from './dashboard/image-choose/image-choose.component';
import { RegisterComponent } from './register/register.component';
import { CartComponent } from './home/cart/cart.component';



@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HomeHeaderComponent,
    HomeFooterComponent,
    MainComponent,
    LoginComponent,
    DashboardComponent,
    ProductComponent,
    CategoryDashboardComponent,
    UserComponent,
    NavbarComponent,
    SideBarComponent,
    ShowProductComponent,
    ProductDetailComponent,
    ProductHomeComponent,
    ImageChooseComponent,
    RegisterComponent,
    CartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    CategoryService,
    UploadImageService,
    ProductService,
    Title,
    AdminService,
    CustomerService,
    UserInfoStorageService,
    CartDataService
  ],
  bootstrap: [AppComponent]
})
export class AppModule  {


}
