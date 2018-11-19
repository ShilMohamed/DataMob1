import { Component, OnInit } from '@angular/core';
import {Authentification} from 'app/Models/Authentification';
import {UserService} from '../Services/user.service';
import {Router} from '@angular/router';
import {User} from '../Models/User';

@Component({
  selector: 'app-authentification',
  templateUrl: './authentification.component.html',
styleUrls: ['./authentification.component.css']
})
export class AuthentificationComponent implements OnInit {
  public auth;
  error: string;
  login: string;
 password: string;
u: User
  constructor(private userservice: UserService, private router: Router ) {
    this.auth = new  Authentification() ;

  }

  ngOnInit() {
  }
  loginUser() {
     this.login = this.auth.login;
    this.password = this.auth.password;
    this.userservice.doAuthenticate (this.login, this.password).subscribe(
      ( data) => { console.log(data);
        if ( data['_body'] === '') {console.log('null');
        this.error = 'Mot de passe incorrecte';
        } else {
        this.u  = JSON.parse(data['_body']) || [] ;
      console.log(this.u);

    console.log(this.u.prenom);


      if (this.u.role === 'Etudiant') {
        this.router.navigate(['Etudiant/:token']);
      }

      if (this.u.role === 'Proffesseur') {
        this.router.navigate(['Prof/:token']);

      }

    }
    }, err  => {
        console.log(err);
      });
  }
  ToUserForm() { console.log('hello'); }
  MDPoublie() { console.log('hi'); }
}
