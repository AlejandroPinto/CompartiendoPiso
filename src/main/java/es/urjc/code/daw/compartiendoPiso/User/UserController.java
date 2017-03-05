package es.urjc.code.daw.compartiendoPiso.User;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.compartiendoPiso.security.LoginController;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired 
	private LoginController loginController;
	
	@PostConstruct
	public void init() {
		userRepository.save(new User ("Adrian","Martin","Sanchez","a@a.com",918115789,"1234","es un parguelas",false,"ROLE_USER"));
		userRepository.save(new User ("Oscar","Sanchez","Sanchez","b@b.com",918115789,"1234","Soy una maquina",false,"ROLE_ADMIN"));
		
	}
	
	@RequestMapping("/user/{id}")
	public String userView(@PathVariable long id, Model model){
		User user = userRepository.findOne(id);
			model.addAttribute("user", user);	
		return "user";
	}
	
	@RequestMapping("/newUser")
	public String newUser(Model model, User user) {
		User saveduser = userRepository.saveAndFlush(user);
		userComponent.setLoggedUser(user);
		model.addAttribute("user", user);
		return "redirect:/user/"+saveduser.getId();
	}
	
	@RequestMapping("/login")
	public String loginAction(@RequestBody String email,Model model){
		User user = userRepository.findByEmail(email);
		if(user != null){
			model.addAttribute("user", user);
			userComponent.setLoggedUser(user);
			return "user/"+user.getId();
		}else{
			model.addAttribute("notFound", true);
			return "redirect:/";
		}
		
	}
}
