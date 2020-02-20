import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
  { 
    path: 'login', 
    loadChildren: () => import('./login/login.module').then(m => m.LoginModule) 
  },
  { 
    path: 'customers', 
    loadChildren: () => import('./customers/customers.module').then(m => m.CustomersModule) 
  },
  { 
    path: 'user', 
    loadChildren: () => import('./user/user.module').then(m => m.UserModule),
  },
  { path: '**', redirectTo: '' }
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }