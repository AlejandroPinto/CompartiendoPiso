package es.urjc.code.daw.compartiendoPiso.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.Offer.OfferRepository;

@Controller
public class AdminController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/admin")
	public String administratorView(Model model, HttpServletRequest request){
		if(userComponent.isLoggedUser()){
		    if(userComponent.getLoggedUser().getRoles().toString().equals("[ROLE_USER, ROLE_ADMIN]")){
		    	List<Offer> offers = offerRepository.findAll();
			    model.addAttribute("admin", request.isUserInRole("ROLE_USER, ROLE_ADMIN"));
				model.addAttribute("offers",offers);
			    return "admin";	
		    }	
		    else{
		    	return "redirect:/signin";
		    }
		}else{
			return "redirect:/signin";
		}
	}
}
