import { Title } from "@angular/platform-browser";
import { Router } from "@angular/router";
import { Customer } from "./../model/Customer";
import { Role } from "./../model/role.enum";
import { FormGroup, FormBuilder } from "@angular/forms";
import { CustomerService } from "./../service/customer/customer.service";
import { AdminService } from "./../service/admin/admin.service";
import { Component, OnInit } from "@angular/core";
import { mergeMap, catchError } from "rxjs/operators";
import {
  UserInfoStorageService,
  UserInfo,
} from "../service/user-info/user-info-storage.service";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.css"],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;

  message: string;

  constructor(
    private adminService: AdminService,
    private customerService: CustomerService,
    private fb: FormBuilder,
    private userInfoStorageService: UserInfoStorageService,
    private router: Router,
    private title: Title
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      username: [""],
      password: [""],
    });
    this.title.setTitle("Login");
  }

  onSubmit() {
    this.message = undefined;
    let role = Role.ADMIN;
    const formValue = this.loginForm.value;
    this.adminService
      .getAdminBy(formValue.username)
      .pipe(
        catchError(err => {
          role = Role.CUSTOMER;
          return this.customerService.findCustomer(formValue.username);
        })
      )
      .subscribe(
        personFinded => {
          if (personFinded.password !== formValue.password) {
            this.message = "Incorrect password !";
            return;
          }
          this.onSuccess({ person: personFinded, role });
        },
        err => {
          this.message = "Username does not exists !";
        }
      );
  }

  onSuccess(userInfo: UserInfo) {
    this.userInfoStorageService.saveUser(userInfo);
    this.router.navigate([
      userInfo.role === Role.ADMIN ? "/dashboard" : "/home",
    ]);
  }
}
