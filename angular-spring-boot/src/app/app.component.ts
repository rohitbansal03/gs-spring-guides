import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { AuthenticationService } from '@shared/app-authentication.service';

import 'rxjs/add/operator/finally';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title: string;
  isAuthenticated: boolean;
 
  constructor(private app: AuthenticationService) {
    this.title = 'Spring Boot - Angular Application';
    this.isAuthenticated = this.app.isAuthenticated;
  }

  logout() {
    this.isAuthenticated = false;
    this.app.logout();
    location.reload(true);
  }

}
