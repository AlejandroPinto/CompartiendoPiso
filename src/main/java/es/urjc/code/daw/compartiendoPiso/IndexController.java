package es.urjc.code.daw.compartiendoPiso;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.compartiendoPiso.User.UserComponent;



@Controller
public class IndexController {
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/")
	public String indexView(Model model, HttpServletRequest request){
		
		model.addAttribute("admin", request.isUserInRole("ROLE_ADMIN"));
		
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
