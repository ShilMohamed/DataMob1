package com.datamob.main.rest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datamob.main.entities.Utilisateur;
import com.datamob.main.serviceImpl.EmailServiceImpl;
import com.datamob.main.services.UserService;
import com.nulabinc.zxcvbn.Zxcvbn;
import com.nulabinc.zxcvbn.Strength;

@RequestMapping(value = "/user")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController

public class UserRest {

	@Autowired
	UserService userService;
	@Autowired
	ApplicationEventPublisher eventPublisher;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private EmailServiceImpl emailService;

	@RequestMapping(value = "/ajoutUser", method = RequestMethod.POST)
	public void addUser(@RequestBody Utilisateur user) {

		userService.saveUser(user);

	}

	@GetMapping(value = "/getAllUsers")
	public List<Utilisateur> getAllUsers() {
		return userService.listAllUsers();
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public void deleteUser(@RequestBody Utilisateur user) {
		userService.deleteUser(user);

	}

	@DeleteMapping("/deleteUser/{id}")
	public void deleteUserById(@PathVariable Long id) {
		userService.deleteUserById(id);
	}

	@RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	public Utilisateur findById(@PathVariable(name = "id") Long id) {

		Utilisateur user = userService.findById(id);

		if (user.equals(null))

			try {
				throw new NotFoundException("id-" + id);
			}

			catch (NotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		return user;
	}

	@RequestMapping(value ="/login/{login}/{password}", method = RequestMethod.GET)

	public Utilisateur doAuthenticate(@PathVariable(name = "login") String a,
			@PathVariable(name = "password") String b) {
		Utilisateur user = userService.doAuthenticate(a, b);

		if (user == null)

		{	try {

				throw new NotFoundException("login-" + a);

			}

			catch (NotFoundException e) {

				e.printStackTrace();

			} }
   System.out.println(user);
		return user;
	}

	@PostMapping("/updateuser")
	public void updateUser(@RequestBody Utilisateur user) {

		userService.updateUser(user);

	}

	/*
	 * @PostMapping("/AffecterRoleAUser") public void AffecterRoleAUser(Role role,
	 * Utilisateur user) {
	 * 
	 * userService.AffecterRoleAUser(role, user);
	 * 
	 * }
	 */
	@RequestMapping(value ="/findthefuckinguser/{login}", method = RequestMethod.GET)

	public Utilisateur findUserByLogin(@PathVariable(name = "login") String login) {
		
		 
		return userService.findUserByLogin(login);
	}

	/*
	 * @GetMapping("/getroleofuser/{login}") public String
	 * GetRoleOfUser(@PathVariable(name="login")String a) { return
	 * userService.GetRoleOfUser(a); }
	 * 
	 * @GetMapping("/getlastUser") public User getlastUser () { return
	 * userService.getlastUser(); }
	 * 
	 * 
	 * @GetMapping(value="/RechercheParNom/{nom}") public List<Utilisateur>
	 * RechercheParNom(@PathVariable(name="nom")String nom){ return
	 * userService.RechercheParNom(nom); }
	 * 
	 * @GetMapping(value="/RechercheParPrenom/{prenom}") public List<Utilisateur>
	 * RechercheParPrenom(@PathVariable(name="prenom")String prenom){ return
	 * userService.RechercheParPrenom(prenom); }
	 * 
	 */

	// Return registration form template
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegistrationPage(ModelAndView modelAndView, Utilisateur user) {
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}

	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void processRegistrationForm(ModelAndView modelAndView, @RequestBody Utilisateur user,
			BindingResult bindingResult, HttpServletRequest request) {

		String role = user.getRole();
		System.out.println("ROLEEE" + role);
		// Lookup user in database by e-mail
		Utilisateur userExists = userService.findByEmail(user.getEmail());
		System.out.println(user.getEmail());
		System.out.println(userExists);

		if (userExists != null) {
			modelAndView.addObject("alreadyRegisteredMessage",
					"Oops!  There is already a user registered with the email provided.");
			modelAndView.setViewName("register");
			bindingResult.reject("email");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("register");
		} else { // new user so we create user and send confirmation e-mail

			if (role.equals("Etudiant")) {
				// Disable user until they click on confirmation link in email
				user.setEnabled(false);

				// Generate random 36-character string token for confirmation link

				user.setConfirmationToken(UUID.randomUUID().toString());
				userService.saveUser(user);

				String appUrl = request.getScheme() + "://" + request.getServerName();

				SimpleMailMessage registrationEmail = new SimpleMailMessage();
				registrationEmail.setTo(user.getEmail());
				registrationEmail.setSubject("Registration Confirmation");
				registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
						+ "http://localhost:4200/Etudiant/" + user.getConfirmationToken());
				registrationEmail.setFrom("noreply@esprit.com");
				System.out.println(registrationEmail);
				emailService.sendEmail(registrationEmail);
			} else if (role.equals("Proffesseur")) {
				// Disable user until they click on confirmation link in email
				user.setEnabled(false);

				// Generate random 36-character string token for confirmation link

				user.setConfirmationToken(UUID.randomUUID().toString());
				String appUrl = request.getScheme() + "://" + request.getServerName();
				userService.saveUser(user);

				SimpleMailMessage registrationEmail = new SimpleMailMessage();
				registrationEmail.setTo(user.getEmail());
				registrationEmail.setSubject("Registration Confirmation");
				registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
						+ "http://localhost:4200/Prof/" + user.getConfirmationToken());
				registrationEmail.setFrom("noreply@esprit.com");
				System.out.println(registrationEmail);
				emailService.sendEmail(registrationEmail);

			}
			// modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been
			// sent to " + user.getEmail());
			// modelAndView.setViewName("register");
		}

		// return modelAndView;
	}

	// Process confirmation link
	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public void showConfirmationPage(@RequestBody String token) {
		System.out.println("hello");
		Utilisateur user = userService.findByConfirmationToken(token);

		if (user == null) { // No token found in DB
			System.out.println("Invalid Token");
		} else { // Token found
			// Set user to enabled
			user.setEnabled(true);

			// Save user
			userService.saveUser(user);
		}

	}

	// Process confirmation link
	/*
	 * @RequestMapping(value = "/confirm", method = RequestMethod.POST) public
	 * ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult
	 * bindingResult,
	 * 
	 * @RequestParam Map requestParams, RedirectAttributes redir) {
	 * 
	 * modelAndView.setViewName("confirm");
	 * 
	 * Zxcvbn passwordCheck = new Zxcvbn();
	 * 
	 * Strength strength = passwordCheck.measure((String)
	 * requestParams.get("password"));
	 * 
	 * if (strength.getScore() < 3) { bindingResult.reject("password");
	 * 
	 * redir.addFlashAttribute("errorMessage",
	 * "Your password is too weak.  Choose a stronger one.");
	 * 
	 * modelAndView.setViewName("redirect:confirm?token=" +
	 * requestParams.get("token")); System.out.println(requestParams.get("token"));
	 * return modelAndView; }
	 * 
	 * // Find the user associated with the reset token Utilisateur user =
	 * userService.findByConfirmationToken((String) requestParams.get("token"));
	 * 
	 * // Set new password
	 * user.setPassword(bCryptPasswordEncoder.encode((CharSequence)
	 * requestParams.get("password")));
	 * 
	 * // Set user to enabled user.setEnabled(true);
	 * 
	 * // Save user userService.saveUser(user);
	 * 
	 * modelAndView.addObject("successMessage", "Your password has been set!");
	 * return modelAndView; }
	 */

}
