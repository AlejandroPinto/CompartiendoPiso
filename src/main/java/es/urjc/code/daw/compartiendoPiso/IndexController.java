package es.urjc.code.daw.compartiendoPiso;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.Offer.OfferRepository;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;
import es.urjc.code.daw.compartiendoPiso.User.UserRepository;


@Controller
public class IndexController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	@RequestMapping("/")
	public String indexView(Model model, HttpServletRequest request){
		
		if (userComponent.isLoggedUser()){
			if(userComponent.getLoggedUser().getRoles().toString().equals("[ROLE_USER, ROLE_ADMIN]")){
				
				model.addAttribute("admin", true);
			}
			model.addAttribute("isLogued",true);
		}
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
		if(userComponent.isLoggedUser()){
			model.addAttribute("isLogued",true);
		}
		
		return "contact";
		
	}
	
	@RequestMapping("/ad-modify")
	public String updateAdView(){
		
		return "adModify";
		
	}
	
	@RequestMapping("/reject")
	public String rejectView(){
		
		return "admin";
		
	}
	
	@RequestMapping("/image/{id}/{fileName}")
	public void dowloadImageUser(@PathVariable String fileName,
			HttpServletResponse res,@PathVariable String id) throws FileNotFoundException, IOException {
		
		UploadFiles up = new UploadFiles();
		File file = new File(up.getFilesFolder()+id+"/", fileName+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}
	
	@RequestMapping("/image/{iduser}/{idoffer}/{fileName}")
	public void dowloadImageOffer(@PathVariable String fileName,
			HttpServletResponse res,@PathVariable String iduser, @PathVariable String idoffer) throws FileNotFoundException, IOException {
		
		UploadFiles up = new UploadFiles();
		File file = new File(up.getFilesFolder()+iduser+"/"+idoffer+"/", fileName+".jpg");

		if (file.exists()) {
			res.setContentType("image/jpeg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath()
					+ ") does not exist");
		}
	}
}
