import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {UserService} from 'app/Services/user.service';

import {HttpModule} from '@angular/http';

import { AppComponent } from './app.component';
import { UserComponent } from './user/user.component';
import {routing} from './app.routing';
import { ConfirmationInscriptionComponent } from './confirmation-inscription/confirmation-inscription.component';
import { ConfirmationInscriptionProfComponent } from './confirmation-inscription-prof/confirmation-inscription-prof.component';
import { AuthentificationComponent } from './authentification/authentification.component';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    ConfirmationInscriptionComponent,
    ConfirmationInscriptionProfComponent,
    AuthentificationComponent,


  ],
  imports: [
    BrowserModule, routing, FormsModule,
    HttpModule
  ],
  providers: [UserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
