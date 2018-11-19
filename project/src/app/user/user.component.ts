import { Component, OnInit } from '@angular/core';
import {User} from 'app/Models/User';
import {UserService} from 'app/Services/user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {


  public user;

  constructor( private  userService: UserService ) {
    this.user = new  User() ;
  }

  ngOnInit() {
  }
  onSubmit() {
   console.log(this.user);
   console.log(this.user.role);

    this.userService.save(this.user).subscribe();

  }
  onRoleSelect(event) {
    console.log(event.target.value);
    this.user.role = event.target.value;
  }




}
