import { NavigationEnd, Router } from '@angular/router';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {
  subcription: Subscription;

  constructor(private router: Router) {

  }

  ngOnInit(): void {
    this.subcription = this.router.events
      .pipe(
        filter(event => event instanceof NavigationEnd)
      )
      .subscribe(() => window.scrollTo(0, 0));
  }

  ngOnDestroy(): void {
    this.subcription.unsubscribe();
  }
}
