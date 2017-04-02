package es.urjc.code.daw.compartiendoPiso.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.UploadFiles;
import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.Offer.Characteristics;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
	
	@Autowired
	private WebService service;
	
	interface CompleteUser extends User.BasicUser, Offer.BasicOffer{}
	interface UserReviews extends User.userAndOffer, Offer.UserOffer, Characteristics.BasicCharacteristics{}
	
	
	@JsonView(UserReviews.class)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable long id){
		User user = service.getUserById(id);	
		System.out.println(user);
		if(user != null){
			return new ResponseEntity<>(user, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(UserReviews.class)
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
			user.setPass(user.getPass());
			User userSaved = service.saveAndFlushUser(user);
			
			return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
		}catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value="/editUser/{id}", method=RequestMethod.PUT)
	public ResponseEntity<User> editUser(@PathVariable long id,@RequestBody User userUpdated){
		
		User user = service.getUserById(id);	
		
		if ((user != null) && (service.isLoggedUser())){	
			
			userUpdated.setId(id);
			userUpdated.setPass(user.getPass());
			service.saveUser(userUpdated);
			
			return new ResponseEntity<>(userUpdated, HttpStatus.OK);
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
			return new ResponseEntity<>("User not found",HttpStatus.NOT_FOUND);
	}
	
	@JsonView(CompleteUser.class)
	@RequestMapping(value = "/setUserPhoto/{id}", method = RequestMethod.PUT, consumes = "multipart/form-data")
	public ResponseEntity<User> setUserPhoto(@PathVariable long id, @RequestParam("file") List<MultipartFile> files ){
		if((service.isLoggedUser())){
			if(service.getLoggedUser().getId() == id){
				User updatedUser = service.getLoggedUser();
				UploadFiles uploadFiles = new UploadFiles();
				String path =  service.getLoggedUser().getId()+"";
				uploadFiles.handleFileUpload(files,path);
				return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);
			}
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
