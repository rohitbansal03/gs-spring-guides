import { Component } from '@angular/core';

import { AuthenticationService } from '@shared/app-authentication.service';

import 'rxjs/add/operator/finally';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  isAuthenticated: boolean = false;
 
  constructor(private app: AuthenticationService) {
    this.title = 'Spring Boot - Angular Application';
    this.isAuthenticated = app.isUserAuthenticated();
  }

  ngOnInit() {
    this.app.isAuthenticated.subscribe((isAuthenticatedVal: boolean) => {
      this.isAuthenticated = isAuthenticatedVal;
    });
  }

  logout() {
    this.isAuthenticated = false;
    this.app.logout();
  }

}
