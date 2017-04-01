package es.urjc.code.daw.compartiendoPiso.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.Offer.Offer;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private WebService service;
	
	interface CompleteUser extends User.BasicUser, Offer.BasicOffer{}
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUserById(@PathVariable long id){
		User user = service.getUserById(id);
		if(user != null){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//falta cuando no esta logueado no mostrarlo
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<User> getLoggedUser(){
		if(service.isLoggedUser()){
			return new ResponseEntity<User>(service.getLoggedUser(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(@RequestBody User user){
		try{
			user.setPass(user.getPass());
			User userSaved = service.saveAndFlushUser(user);
			
			return new ResponseEntity<User>(userSaved, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/editUser", method=RequestMethod.PUT)
	public ResponseEntity<User> editUser(@RequestBody User user){
		if((service.isAdmin()) || (user.getId()==service.getUserId())){
			//añadir metodo de editar
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
