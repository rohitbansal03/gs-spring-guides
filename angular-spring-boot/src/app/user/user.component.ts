import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import { AuthenticationService } from '@app/shared/app-authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UsersComponent implements OnInit {
 
 constructor(private authService: AuthenticationService,
      private router: Router) {
  }
 
  ngOnInit() {
    this.authService.isAuthenticated.subscribe((isAuthenticated: boolean) => {
      if(!isAuthenticated) {
        this.router.navigate(['/login']);
        return ;
      }
    });
  }
  
}
