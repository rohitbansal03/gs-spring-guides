import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { UserRoutingModule } from './user-routing.module';
import { UsersComponent } from './user.component';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';
import { UserService } from './user-service/user-service';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    UsersComponent,
    UserListComponent,
    UserFormComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    UserRoutingModule
  ], 
  providers: [ 
    UserService
  ]
})
export class UserModule { }
