package es.urjc.code.daw.compartiendoPiso.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired 
	private UserRepository userRepository;
	@Autowired 
	private UserComponent userComponent;
	

	
	
//	@JsonView
//	@RequestMapping(value="/user", method=RequestMethod.PUT)
//	public ResponseEntity<User> modifyUser(@RequestBody User userUpdated){
//		if(userComponent.isLoggedUser()){
//			userUpdated = userRepository.saveAndFlush(userUpdated);
//			return new ResponseEntity<User>(userUpdated,HttpStatus.OK);
//		}else{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//	}
}
