package es.urjc.code.daw.compartiendoPiso.User;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.compartiendoPiso.Offer.Characteristics;
import es.urjc.code.daw.compartiendoPiso.Offer.CharacteristicsRepository;
import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.Offer.OfferRepository;


@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@Autowired
	private CharacteristicsRepository characteristicsRepository;
	
	@Autowired
	private OfferRepository offerRepository;
		
	@PostConstruct
	public void init() {
		userRepository.save(new User ("Adrian","Martin","Sanchez","a@a.com",918115789,"1234","es un parguelas",false,"ROLE_USER"));
		userRepository.save(new User ("Oscar","Sanchez","Sanchez","b@b.com",918115789,"1234","Soy una maquina",false,"ROLE_ADMIN"));
		User user = userRepository.saveAndFlush(new User ("JUAN","Sanchez","Sanchez","c@c.com",918115789,"1234","Soy una maquina",false,"ROLE_USER"));
		userRepository.save(user);
		Offer offer = new Offer("Piso","Piso bastante guapo",222,"Se alquila piso en Móstoles, en la zona del Soto. Piso de 4 dormitorios reformado, con armarios empotrados, calefacción central, y cocina eléctrica. Zona tranquila y segura, especialmente para familias.","Madrid","El alamo","callejuelos",200,2,1,1);
		offer.setUser(user);
		offerRepository.save(offer);
	}
	
	@RequestMapping("/user")
	public String userloginView(Model model){
		if((userComponent.isLoggedUser())){
			long id = userComponent.getLoggedUser().getId();	
			User user = userRepository.findOne(id);
			model.addAttribute("user", user);
			model.addAttribute("offers",user.getOffers());
			if(userComponent.getLoggedUser().getId() == user.getId()){
				model.addAttribute("isLoged",true);			
			}
			model.addAttribute("isLoged",false);
			return "user";
		}else{
			
			return "redirect:/signin";
		}
	}	
	
	@RequestMapping("/user/{id}")
	public String userView(@PathVariable long id, Model model){
		if(userComponent.isLoggedUser()){
			User user = userRepository.findOne(id);
			model.addAttribute("user", user);
			model.addAttribute("offers",user.getOffers());
			if(userComponent.getLoggedUser().getId() == user.getId()){
				model.addAttribute("isLoged",true);
			}
			model.addAttribute("isLoged",false);
			return "user";
		}else{
			return "redirect:/signin";
		}
			
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
