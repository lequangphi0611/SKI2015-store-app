import { Role } from './../../model/role.enum';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { Injectable } from '@angular/core';
import { Person } from 'src/app/model/Person';

export interface UserInfo {
  person: Person;
  role: Role;
}

@Injectable({
  providedIn: 'root'
})
export class UserInfoStorageService {

  static readonly KEY = 'user';

  private user: BehaviorSubject<UserInfo> = new BehaviorSubject<UserInfo>(null);

  user$: Observable<UserInfo> = this.user.asObservable();

  constructor() {
    this.refresh();
   }

  saveUser({person, role}): void {
    sessionStorage.setItem(UserInfoStorageService.KEY, JSON.stringify({person, role}));
    this.refresh();
  }

  removeUser(): void {
    sessionStorage.removeItem(UserInfoStorageService.KEY);
  }

  getUser(): UserInfo {
    return JSON.parse(sessionStorage.getItem(UserInfoStorageService.KEY));
  }

  refresh(): void {
    this.user.next(this.getUser());
  }
}
