import { Injectable } from '@angular/core';
import {User} from 'app/Models/User' ;
import {map} from 'rxjs/operators';
import {HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Http, RequestOptions, ResponseContentType} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class UserService {

  private User = new User();

  constructor(private http: Http ) {
  }

  save(user: User) {

  return this.http.post('http://localhost:8080/user/register', user);

  }
  confirmInsciption(token) {
    console.log('salut');
    return this.http.post('http://localhost:8080/user/confirm', token );



  }
  doAuthenticate(login: string, password: string)  {
    // return this.http.post('http://localhost:8088/adherent/ajoutAdherent',JSON.stringify(adherent)) ;
    // @ts-ignore
    return this.http.get('http://localhost:8080/user/login/' + login + '/' + password , login + password);

  }

}
