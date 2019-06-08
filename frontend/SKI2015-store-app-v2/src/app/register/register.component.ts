import { Router } from '@angular/router';
import { Role } from './../model/role.enum';
import { map, mergeMap, catchError, switchMap } from 'rxjs/operators';
import { Customer } from './../model/Customer';
import { Title } from '@angular/platform-browser';
import { FormGroup, FormBuilder, AbstractControl, ValidatorFn, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../service/customer/customer.service';
import { UserInfoStorageService } from '../service/user-info/user-info-storage.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private title: Title,
    private customerService: CustomerService,
    private userInfoService: UserInfoStorageService,
    private router: Router
  ) { }

  ngOnInit() {
    this.buildForm();
    this.title.setTitle('Register');
  }

  buildForm(): void {
    this.registerForm = this.fb.group({
      firstname: ['', [Validators.required]],
      lastname: ['', [Validators.required]],
      birthday: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
      confirm: ['', [Validators.required]]
    }, { validators: confirmPasswordValidator });
  }

  get password(): AbstractControl {
    return this.registerForm.get('password');
  }

  get confirm(): AbstractControl {
    return this.registerForm.get('confirm');
  }

  get email(): AbstractControl {
    return this.registerForm.get('email');
  }

  get birthday(): AbstractControl {
    return this.registerForm.get('birthday');
  }

  get lastname(): AbstractControl {
    return this.registerForm.get('lastname');
  }

  get firstname(): AbstractControl {
    return this.registerForm.get('firstname');
  }

  onSubmit(): void {
    const customer = this.registerForm.value as Customer;
    this.customerService
      .existsEmail(customer.email)
      .pipe(
        switchMap((isExists) => {
          if (isExists) {
            throw new Error(`Email =  '${customer.email}' is already exists !`);
          }
          return this.customerService.insertCustomer(customer);
        })
      )
      .subscribe(
        (customerSaved) => {
          this.onSuccess(customerSaved);
        },
        (err: Error) => {
          this.onError(err);
        }
      );
  }

  onSuccess(customer: Customer) {
    this.userInfoService.saveUser({ person: customer, role: Role.CUSTOMER });
    this.router.navigate(['/home']);
  }

  onError(err: Error) {
    this.email.setErrors({ 'exists': true });
  }

}


function confirmPasswordValidator(group: FormGroup) {

  const password = group.get('password').value;
  const confirmPass = group.get('confirm').value;

  if (password && confirmPass && password !== confirmPass) {
    return { notSame: true };
  }

  return null;
}
