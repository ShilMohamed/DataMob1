package com.datamob.main.serviceImpl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.datamob.main.entities.Utilisateur;
import com.datamob.main.repositories.UserRepo;
import com.datamob.main.services.UserService;



@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepo repo ;
	
	
	@Autowired 
	PasswordEncoder pe ;
	
	@Override
	public Utilisateur saveUser(Utilisateur user) {

		String pswd = user.getPassword() ;
		pswd = pe.encode(pswd) ;
		user.setPassword(pswd);
		
		return repo.save(user);
		
	}

	@Override
	public Utilisateur updateUser(Utilisateur user) {
	
		Utilisateur u =new Utilisateur();
		
		u = repo.findById((long)user.getId()).get();
		
		return repo.save(u);
		
	}

	@Override
	public void deleteUser(Utilisateur user) {

		repo.delete(user);
		
	}

	@Override
	public void deleteUserById(Long id) {
	
		repo.deleteById(id);
		
	}

	@Override
	public List<Utilisateur> listAllUsers() {

		OutputStream img;
		//img = 
		return repo.findAll();

	}

	@Override
	public Utilisateur findById(Long id) {
		return repo.findById(id).get();
	}


	@Override
	public Utilisateur findByEmail(String email) {
		// TODO Auto-generated method stub
		return repo.findByEmail(email);
	}

	@Override
	public Utilisateur findByConfirmationToken(String confirmationToken) {
		// TODO Auto-generated method stub
		return repo.findByConfirmationToken(confirmationToken);
	}

	
	


	@Override
	public Utilisateur doAuthenticate(String login, String pwd)
	{
		Utilisateur user = findUserByLogin(login) ;
		System.out.println( pe.matches(pwd, user.getPassword()));
		
		 if (!user.equals(null))
		 { 
			 if (pe.matches(pwd, user.getPassword()) ){
			System.out.println("uuuuuuuuu "+ user);

				 return user ;
		 }
     }
	 	return null;
	 
}
/*
	 @Override
	public void AffecterRoleAUser(Role role, User user) {
		
		User u = repo.findById(user.getId()).get();
		Role r = rolerepo.findById(role.getId()).get();
		
		u.setRole(r);

		List<User> users = new ArrayList<>();
		
		users= r.getUsers();
		users.add(u);
		
		r.setUsers(users);
		
		repo.save(u);
		rolerepo.save(r);
		
		
	}
	
	@Override
	public String GetRoleOfUser(String login)
	{
		User u = new User();
		
		u= findUserByLogin(login) ;
		
		Role r = new Role();
		
		r.setLibelle(u.getRole().getLibelle());
		
		return r.getLibelle();
	}

	@Override
	public User getlastUser() {
		return repo.getlastUser();
	}
	
	

	@Override
	public List<User> RechercheParNom(String nom) {
		return repo.RechercheParNom(nom);
	}



	@Override
	public List<User> RechercheParPrenom(String prenom) {
		return repo.RechercheParPrenom(prenom);
	}	
	*/

	@Override
	public Utilisateur findUserByLogin(String login) {
		// TODO Auto-generated method stub
		return repo.findUserByLogin(login);
	}

}
