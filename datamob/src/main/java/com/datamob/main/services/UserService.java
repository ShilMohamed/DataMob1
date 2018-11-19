package com.datamob.main.services;

import java.util.List;

import com.datamob.main.entities.Utilisateur;



public interface UserService {

	 Utilisateur saveUser(Utilisateur user);
		
	 Utilisateur updateUser(Utilisateur user);

	 void deleteUser(Utilisateur  user);
	 	
	 void deleteUserById(Long id);
	
	 List <Utilisateur> listAllUsers() ;
		
 	 public Utilisateur findById(Long id);
 	public Utilisateur findByEmail(String email);
	public Utilisateur findByConfirmationToken(String confirmationToken);

	 Utilisateur findUserByLogin(String login);
  	 Utilisateur doAuthenticate(String login , String pwd) ;
 	
 	// public void AffecterRoleAUser(Role role, User user);	
 	 
 	 //public String GetRoleOfUser(String login);
 	 
 	 //User getlastUser();
 	 
 	 //List <User> RechercheParNom(String nom);
     
     //List <User> RechercheParPrenom(String prenom);
	
}


