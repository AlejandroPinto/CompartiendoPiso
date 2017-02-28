package es.urjc.code.daw.compartiendoPiso.User;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@PostConstruct
	public void init() {
		userRepository.save(new User ("Adrian","Martin","Sanchez","a@a.com",918115789,"1234","es un parguelas",false));
		userRepository.save(new User ("Oscar","Sanchez","Sanchez","b@b.com",918115789,"1234","Soy una maquina",false));
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
}
