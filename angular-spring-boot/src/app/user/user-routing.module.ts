import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsersComponent } from './user.component';
import { AuthGuard } from '@app/shared/app-auth-guard';
import { UserListComponent } from './user-list/user-list.component';
import { UserFormComponent } from './user-form/user-form.component';

const routes: Routes = [
  {
    path: '', 
    component: UsersComponent,
    canActivate: [AuthGuard], 
    children: [
      { 
        path: '', 
        redirectTo: 'list'
      },
      { 
        path: 'list', 
        component: UserListComponent
      },
      { 
        path: 'add', 
        component: UserFormComponent
      }
    ]
  }
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule { }
