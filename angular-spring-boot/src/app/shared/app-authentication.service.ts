import { Injectable, EventEmitter, Output } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

import { environment } from '@environment/environment';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    @Output() isAuthenticated: EventEmitter<boolean> = new EventEmitter();

    constructor(private http: HttpClient) { 
        if(localStorage.getItem('currentUser')) {
            this.isAuthenticated.emit(true);
        } else {
            this.isAuthenticated.emit(false);
        }
    }

    login(username: string, password: string) {
        const headers = new HttpHeaders(username ? {
            authorization : 'Basic ' + btoa(username + ':' + password)
        } : {});
        return this.http.get<any>(this.getUrl('/user'), { headers: headers })
            .pipe(map(user => {
                // login successful if there's a user in the response
                if (user) {
                    // store user details and basic auth credentials in local storage 
                    // to keep user logged in between page refreshes
                    user.authdata = window.btoa(username + ':' + password);
                    localStorage.setItem('currentUser', JSON.stringify(user));
                    this.isAuthenticated.emit(true);
                }
                return user;
            }));
    }

    logout() {
        localStorage.removeItem('currentUser');
        this.isAuthenticated.emit(false);
    }

    getUrl(resource: string) {
        return environment.baseUrl + resource;
    }

}