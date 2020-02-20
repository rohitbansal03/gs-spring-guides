import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { UserService } from '../user-service/user-service';
import { AuthenticationService } from '@app/shared/app-authentication.service';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
 
  users: User[];
 
  constructor(private userService: UserService, 
      private authService: AuthenticationService,
      private router: Router) {
  }
 
  ngOnInit() {
    this.authService.isAuthenticated.subscribe((isAuthenticated: boolean) => {
      if(!isAuthenticated) {
        this.router.navigate(['/login']);
        return ;
      }
    });
    this.userService.findAll().subscribe(data => {
      this.users = data;
    });
  }
  
}