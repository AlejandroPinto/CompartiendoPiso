package es.urjc.code.daw.compartiendoPiso;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Controller;
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
	
	@RequestMapping("/contact")
	public String contactView(){
		
		return "contact";
		
	}
	
	@RequestMapping("/user/{id}")
	public String userView(@PathVariable AtomicInteger id){
		
		return "contact";
		
	}
	
	@RequestMapping("/user")
	public String userView(){
		
		return "user";
		
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
	public String administratorView(){
		
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
