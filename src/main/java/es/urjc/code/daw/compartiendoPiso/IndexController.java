package es.urjc.code.daw.compartiendoPiso;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.Offer.OfferRepository;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;




@Controller
public class IndexController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/")
	public String indexView(Model model, HttpServletRequest request){
		model.addAttribute("admin", request.isUserInRole("ROLE_ADMIN"));
		if(userComponent.isLoggedUser()){
			model.addAttribute("isLogued",true);
		}
		
		return "index";
		
	}
	
	@RequestMapping("/search")
	public String indexView(Model model,@RequestParam String queryBox,
			@RequestParam float priceTo, @RequestParam float priceFrom,
			@RequestParam String type, @RequestParam int bathroom,
			@RequestParam int rooms, @RequestParam int area){
		System.out.println(queryBox+priceFrom+priceTo+type+bathroom+rooms+area);
//		if (queryBox.isEmpty())
//			queryBox="empty";
		List<Offer> offers = offerRepository.masterQuery(queryBox,type,priceFrom,priceTo,area,rooms,bathroom);
		model.addAttribute("offers",offers);
		return "index";	
	}
	
	@RequestMapping("/logout")
	public String disconnectView(){
		
		return "index";
		
	}
	
	@RequestMapping("/register")
	public String registerView(){
		
		return "register";
	}
	
	@RequestMapping("/signin")
	public String signinView(){
		
		return "signin";
		
	}
	
	@RequestMapping("/contact")
	public String contactView(Model model, HttpServletRequest request){
		
		model.addAttribute("admin", request.isUserInRole("ROLE_ADMIN"));
		
		return "contact";
		
	}
	
//	@RequestMapping("/newAd")
//	public String newAnnouncementView(){
//		
//		return "newAd";
//		
//	}
	@RequestMapping("/ad-modify")
	public String updateAdView(){
		
		return "adModify";
		
	}
	
//	@RequestMapping("/admin")
//	public String administratorView(Model model, HttpServletRequest request){
//	    	
//	    model.addAttribute("admin", request.isUserInRole("ROLE_ADMIN"));
//		
//	    return "admin";
//		
//	}
	
	@RequestMapping("/reject")
	public String rejectView(){
		
		return "admin";
		
	}
	@RequestMapping("/accept-offer")
	public String acceptOffer(){
		
		return "admin";
		
	}

}
