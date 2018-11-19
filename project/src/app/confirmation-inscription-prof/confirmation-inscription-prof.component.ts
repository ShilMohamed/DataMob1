import { Component, OnInit } from '@angular/core';
import {UserService} from '../Services/user.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-confirmation-inscription-prof',
  templateUrl: './confirmation-inscription-prof.component.html',
  styleUrls: ['./confirmation-inscription-prof.component.css']
})
export class ConfirmationInscriptionProfComponent implements OnInit {
token: string;
  constructor(private route: ActivatedRoute, private router: Router, private  userservice: UserService ) { }

  ngOnInit() {
    const allParams = this.route.snapshot.params ;
    this.token  = allParams.token ;
    console.log('token' + this.token);
    this.userservice.confirmInsciption(this.token).subscribe();
  }

}
