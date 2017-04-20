package es.urjc.code.daw.compartiendoPiso.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.daw.compartiendoPiso.UploadFiles;
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
		userRepository.save(new User ("Adrian","Martin","Sanchez","a@a.com",918115789,"1234","es un parguelas",false,"aaaaa","ROLE_USER"));
		userRepository.save(new User ("Oscar","Sanchez","Sanchez","b@b.com",918115789,"1234","Soy una maquina",false,"ee","ROLE_USER","ROLE_ADMIN"));
		User user = userRepository.saveAndFlush(new User ("JUAN","Sanchez","Sanchez","w@w.com",918115789,"1234","Soy una maquina",false,"eeeeeee","ROLE_USER"));
		userRepository.save(user);
		for(int i = 1; i<30; i++){
			Offer offer = new Offer("Piso","Piso bastante guapo"+i,222,"Se alquila piso en Móstoles, en la zona del Soto. Piso de 4 dormitorios reformado, con armarios empotrados, calefacción central, y cocina eléctrica. Zona tranquila y segura, especialmente para familias.","Madrid","El alamo","callejuelos",200,2,1,1);
			offer.setUser(user);
			offerRepository.save(offer);
		}
	}
	
	@RequestMapping("/user")
	public String userloginView(Model model, HttpServletRequest request){
		
		if((userComponent.isLoggedUser())){
			long id = userComponent.getLoggedUser().getId();	
			User user = userRepository.findOne(id);
			model.addAttribute("user", user);
			model.addAttribute("offers",user.getOffers());
			if(userComponent.getLoggedUser().getId() == user.getId()){
				model.addAttribute("isLoged",true);			
			}
			model.addAttribute("admin", request.isUserInRole("ROLE_ADMIN"));
			return "user";
		}else{
			return "redirect:/signin";
		}
	}	
	
	@RequestMapping("/user/{id}")
	public String userView(@PathVariable long id, Model model){
		//MIRAR, YA QUE NO TIENE QUE ESTAR LOGUEADO EL USUARIO PARA VER ESTA VISTA
		if(userComponent.isLoggedUser()){
			User user = userRepository.findOne(id);
			model.addAttribute("user", user);
			model.addAttribute("offers",user.getOffers());
			if(userComponent.getLoggedUser().getId() == user.getId()){
				model.addAttribute("isLoged",true);
			}
			
			return "user";
		}else{
			return "redirect:/signin";
		}
			
	}
	
	@RequestMapping("/newUser")
	public String newUser(Model model, User user, @RequestParam("file") List<MultipartFile> files) {
		
		User saveduser = userRepository.saveAndFlush(user);
		userComponent.setLoggedUser(user);
		model.addAttribute("user", user);
		
		String path = saveduser.getId()+"/";
		
		UploadFiles uploadedFiles = new UploadFiles();
		uploadedFiles.handleFileUpload(files,path);
		
		return "redirect:/user";
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
	
	@RequestMapping("/edit-user")
	public String editUser(Model model){
		if(userComponent.isLoggedUser()){
			model.addAttribute("user",userComponent.getLoggedUser());
			return "editUser";
		}else{
			return "redirect:/signin";
		}
	}
	
	@RequestMapping(value="/edit-user", method=RequestMethod.POST)
	public String editUser(Model model, User editUser, @RequestParam("file") List<MultipartFile> files){
		if(userComponent.isLoggedUser()){
			User userLogin = userComponent.getLoggedUser();
				editUser.setId(userLogin.getId());
				User user = userRepository.saveAndFlush(editUser);
				userComponent.setLoggedUser(user);
				
				String path = user.getId()+"/";
				UploadFiles uploadedFiles = new UploadFiles();
				uploadedFiles.handleFileUpload(files,path);
				
				return "redirect:/user";
		}else{
			model.addAttribute("notFound", true);
			return "redirect:/signin";
		}
	}	
}
