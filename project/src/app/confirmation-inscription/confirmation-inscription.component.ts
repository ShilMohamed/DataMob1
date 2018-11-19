import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute, Params} from '@angular/router';
import {UserService} from '../Services/user.service';

@Component({
  selector: 'app-confirmation-inscription',
  templateUrl: './confirmation-inscription.component.html',
  styleUrls: ['./confirmation-inscription.component.css']
})
export class ConfirmationInscriptionComponent implements OnInit {

  token: string;
  private sub: any;

  constructor(private route: ActivatedRoute, private router: Router, private  userservice: UserService) {

  }

  ngOnInit() {
    const allParams = this.route.snapshot.params ;
    this.token  = allParams.token ;
    console.log('token' + this.token);
    this.userservice.confirmInsciption(this.token).subscribe();

  }


}
