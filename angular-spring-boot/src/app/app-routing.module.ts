import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AuthGuard } from '@shared/app-auth-guard';

import { LoginComponent } from '@login/login.component';
import { UserListComponent } from '@user/user-list/user-list.component';
import { UserFormComponent } from '@user/user-form/user-form.component';


const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'users', component: UserListComponent, canActivate: [AuthGuard] },
  { path: 'user/add', component: UserFormComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: '' }
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }