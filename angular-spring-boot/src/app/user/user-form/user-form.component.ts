import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user-service/user-service';
import { User } from '../user';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
 
  roles: string[];
  user: User;
  response: { 'error': boolean, 'errorMsgs': string[] };
 
  constructor(
    private route: ActivatedRoute, private router: Router, private userService: UserService) {
    this.user = new User();
    this.response = {error: false, errorMsgs: []};

    // Initialize roles for user-registration page
    userService.getRoles().subscribe(result => {
        this.roles = result;
      }, error => console.error('Error while fetching roles')
    );
  }
 
  onSubmit(userForm: NgForm) {
    if(userForm.valid) {
        this.userService.save(this.user).subscribe(result => {
          this.router.navigate(['/users']);
          console.log(result);
        }, 
        errorObj => {
          this.response.error = true;
          this.response.errorMsgs = errorObj.errors;
        }
      );
    }
  }

}
