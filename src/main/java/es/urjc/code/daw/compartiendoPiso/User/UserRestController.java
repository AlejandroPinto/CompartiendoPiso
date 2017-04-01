package es.urjc.code.daw.compartiendoPiso.User;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<String> getUser(@PathVariable long id){
		User user = service.getUserById(id);	
		if(user != null && service.isLoggedUser()){
			return new ResponseEntity<>("Usuario borrado", HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity<User> getUserLogged(){
		User user = service.getUserById(service.getUserId());	
		if(user != null && service.isLoggedUser()){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(@RequestBody User user){
		try{
			service.saveUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}catch(Exception ex){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}
	
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/editUser", method=RequestMethod.PUT)
	public ResponseEntity<User> editUser(@RequestBody User userUpdated,@PathVariable long id){
		
		User user = service.getUserRepository().findOne(id);
		
		if ((user != null) && (user.getId() == userUpdated.getId())) {	
			
			if (service.isLoggedUser() || service.isAdmin()){
				user.setName(userUpdated.getName());	
				user.setFirstLastName(userUpdated.getFirstLastName());
				user.setSecondLastName(userUpdated.getSecondLastName());
				user.setEmail(userUpdated.getEmail());
				user.setPhone(userUpdated.getPhone());
				user.setDescription(userUpdated.getDescription());
				user.setPass(userUpdated.getPass());
	
				service.saveUser(user);
				return new ResponseEntity<>(userUpdated, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
			
	}
	
	//El delete no se si es necesario pero ahi lo dejo
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/deleteUser/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<String> deleteUser(@PathVariable long id){
		
			if(service.isAdmin()){
			service.deleteUser(id);
			return new ResponseEntity<>("Usuario borrado", HttpStatus.OK);
			}else
			return new ResponseEntity<>("Error",HttpStatus.NOT_FOUND);
	}
}
