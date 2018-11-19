import {ModuleWithProviders} from '@angular/core' ;
import {Routes, RouterModule} from '@angular/router';
import {UserComponent} from './user/user.component';
import {ConfirmationInscriptionComponent} from './confirmation-inscription/confirmation-inscription.component';
import {ConfirmationInscriptionProfComponent} from './confirmation-inscription-prof/confirmation-inscription-prof.component';
import { AuthentificationComponent } from './authentification/authentification.component';

const  appRoutes: Routes = [

  {
    path : 'formulaire' ,
    component : UserComponent

  },
  {
    path : 'Etudiant/:token' ,
    component : ConfirmationInscriptionComponent

  },
  {
    path : 'Prof/:token' ,
    component : ConfirmationInscriptionProfComponent

  },
  {
    path : 'login' ,
    component : AuthentificationComponent

  }





];
export  const  routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
