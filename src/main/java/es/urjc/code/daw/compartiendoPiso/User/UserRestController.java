package es.urjc.code.daw.compartiendoPiso.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<User> getUser(@PathVariable long id){
		User user = service.getUserById(id);
		if(user != null && service.isLoggedUser()){
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	//revisar
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/addUser", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public User addUser(@RequestBody User user){
		service.saveUser(user);
		return user;
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
