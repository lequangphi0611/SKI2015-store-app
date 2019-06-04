import { map } from 'rxjs/operators';
import { Admin } from './../../model/Admin';
import { UserInfoStorageService } from './../../service/user-info/user-info-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-side-bar',
  templateUrl: './side-bar.component.html',
  styleUrls: ['./side-bar.component.css']
})
export class SideBarComponent implements OnInit {

  user: Admin;

  constructor(private userInfoStorageService: UserInfoStorageService) { }

  ngOnInit() {
    this.userInfoStorageService.user$
      .pipe(
        map(userInfo => userInfo.person),
        map((person) => person as Admin)
      )
      .subscribe((admin) => this.user = admin);
  }

}
