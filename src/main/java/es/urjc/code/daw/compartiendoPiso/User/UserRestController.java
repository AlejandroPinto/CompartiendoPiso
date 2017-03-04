package es.urjc.code.daw.compartiendoPiso.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

	@Autowired 
	private UserRepository userRepository;
	
	@RequestMapping(value="user/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> modifyUser(@PathVariable long id, @RequestBody User userUpdated){
		User user = userRepository.findOne(id);
		userUpdated.setId(id);
		userUpdated = userRepository.saveAndFlush(userUpdated);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
}
