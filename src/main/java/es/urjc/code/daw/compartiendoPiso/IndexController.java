package es.urjc.code.daw.compartiendoPiso;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String indexView(){
		
		return "index";
		
	}
	
	@RequestMapping("/disconnect")
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
	
	@RequestMapping("/offer-room")
	public String offerRoomView(){
		
		return "offer-room";
		
	}
	
	@RequestMapping("/offer-house")
	public String offerHouseView(){
		
		return "offer-house";
		
	}
	
	@RequestMapping("/offer-room/{id}")
	public String offerRoomView(@PathVariable AtomicInteger id){
		
		return "offer-room";
		
	}
	
	@RequestMapping("/offer-house/{id}")
	public String offerHouseView(@PathVariable AtomicInteger id){
		
		return "offer-house";
		
	}
	
	@RequestMapping("/contact")
	public String contactView(){
		
		return "contact";
		
	}
	
	@RequestMapping("/newAd")
	public String newAnnouncementView(){
		
		return "newAd";
		
	}
	@RequestMapping("/ad-modify")
	public String updateAdView(){
		
		return "adModify";
		
	}
	
	@RequestMapping("/admin")
	public String administratorView(Model model, HttpServletRequest request){
	    	
	    model.addAttribute("admin", request.isUserInRole("ADMIN"));
		
	    return "admin";
		
	}
	
	@RequestMapping("/reject")
	public String rejectView(){
		
		return "admin";
		
	}
	@RequestMapping("/accept-offer")
	public String acceptOffer(){
		
		return "admin";
		
	}

}
