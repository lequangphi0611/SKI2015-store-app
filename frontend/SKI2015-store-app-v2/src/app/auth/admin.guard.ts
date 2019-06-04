import { Injectable } from "@angular/core";
import {
  CanActivate,
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  UrlTree,
  Router,
} from "@angular/router";
import { Observable } from "rxjs";
import {
  UserInfoStorageService,
  UserInfo,
} from "../service/user-info/user-info-storage.service";
import { Role } from "../model/role.enum";

@Injectable({
  providedIn: "root",
})
export class AdminGuard implements CanActivate {
  constructor(
    private router: Router,
    private userInfoService: UserInfoStorageService
  ) {}

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    return this.check();
  }

  check() {
    const user: UserInfo = this.userInfoService.getUser();
    if (user.role !== Role.ADMIN) {
      this.router.navigate(["/home/index"]);
      return false;
    }
    return true;
  }
}
