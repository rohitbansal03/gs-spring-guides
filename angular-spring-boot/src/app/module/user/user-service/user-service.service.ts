import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../model/user';
import { Observable } from 'rxjs/Observable';
import { environment } from '@environment/environment'

@Injectable()
export class UserService {
 
  private rolesUrl: string;
  private usersUrl: string;
 
  constructor(private http: HttpClient) {
    this.usersUrl = environment.baseUrl + '/users';
    this.rolesUrl = environment.baseUrl + '/roles';
  }

  public findAll(): Observable<User[]> {
    var header = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa('rohit@abc.com:password')
      })
    }
    return this.http.get<User[]>(this.usersUrl, header);
  }

  public getRoles(): Observable<string[]> {
    var header = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa('rohit@abc.com:password')
      })
    }
    return this.http.get<string[]>(this.rolesUrl, header);
  }
 
  public save(user: User) {
    var header = {
      headers: new HttpHeaders({
        'Authorization': 'Basic ' + btoa('rohit@abc.com:password')
      })
    }
    return this.http.post<User>(this.usersUrl, user, header);
  }
  
}
