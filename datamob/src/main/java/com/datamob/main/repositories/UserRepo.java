package com.datamob.main.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datamob.main.entities.Utilisateur;


@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<Utilisateur, Long> {

/*
	@Query("SELECT t FROM User t where t.id = (select Max(t.id) from t) ")
	Utilisateur getlastUser();
	
	@Query("SELECT t FROM User t where t.nom like %:nom%")
	List<Utilisateur> RechercheParNom(@Param("nom") String nom);
	
	@Query("SELECT t FROM User t where t.prenom like %:prenom%")
	List<Utilisateur> RechercheParPrenom(@Param("prenom") String prenom);
	*/
	 Utilisateur findUserByLogin(String login);
	
	   Utilisateur findByEmail(String email);
	   Utilisateur findByConfirmationToken(String confirmationtoken);
	
}
