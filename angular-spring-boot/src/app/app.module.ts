import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule }    from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { ErrorInterceptor, BasicAuthInterceptor } from '@shared/app-http-interceptor';
import { AppComponent } from './app.component';
import { LoginComponent } from '@login/login.component';
import { UserListComponent } from '@user/user-list/user-list.component';
import { UserFormComponent } from '@user/user-form/user-form.component';

import { UserService } from '@user/user-service/user-service';
 
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    UserFormComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ UserService, 
    { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
