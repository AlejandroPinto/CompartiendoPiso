package es.urjc.code.daw.compartiendoPiso.Offer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.daw.compartiendoPiso.UploadFiles;
import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;
import es.urjc.code.daw.compartiendoPiso.User.UserRepository;
import es.urjc.code.daw.compartiendoPiso.review.Review;
import es.urjc.code.daw.compartiendoPiso.review.ReviewRepository;


@Controller
public class OfferController {
	
	@Autowired
	private WebService webService;
		
	
	@PostConstruct
	public void init() {
		
		User user = webService.saveAndFlushUser(new User ("JUAN","Sanchez","Sanchez","c@c.com",918115789,"1234","Soy una maquina",false,"ROLE_USER"));
		
		Offer offer = new Offer("Chale","Chalé bastante moderno",222,"Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.","Madrid","Navalcarnero","el olivar",200,2,1,1);
		offer.setUser(user);
		webService.saveOffer(offer);
		Characteristics c1 = new Characteristics("Terraza", true);
		Characteristics c2 = new Characteristics("Balcón", true);
		Characteristics c3 = new Characteristics("Alarma", true);
		c1.setOffer(offer);
		c2.setOffer(offer);
		c3.setOffer(offer);
		webService.saveCharacteristics(c1);
		webService.saveCharacteristics(c2);
		webService.saveCharacteristics(c3);	
		
		
		
		User user2 = webService.saveAndFlushUser(new User ("JUAN","Sanchez","Sanchez","d@d.com",918115789,"1234","Soy una maquina",false,"ROLE_USER"));
		for(int i=0;i<10;i++){
			Review review = new Review(5,"regular"+i);
			review.setOfferReview(offer);
			review.setUserReview(user2);
			webService.saveReview(review);
		}
		
	}

	@RequestMapping("/offer/{id}")
	public String verAnuncio(Model model, @PathVariable long id, Pageable pageable, @RequestParam int page, @RequestParam int size) {
		if(webService.isLoggedUser()){
			model.addAttribute("isLogued",true);
		}
		Offer offer = webService.getOfferById(id);		
		model.addAttribute("offer", offer);
		Page<Review> reviews = webService.findByOfferReview(offer, page,size);
		model.addAttribute("reviews", reviews);
		model.addAttribute("numReviews",offer.getReviews().size());
		
		model.addAttribute("showNext", !reviews.isLast());
		model.addAttribute("showPrev", !reviews.isFirst());
		for(int i=1; i<=5; i++){
			if(!reviews.isLast()){
				model.addAttribute("nextPage"+i, reviews.getNumber()+i);
			}
			if(!reviews.isFirst()){
				model.addAttribute("nextPage"+i, reviews.getNumber()+i);
			}
			model.addAttribute("nextPage", reviews.getNumber()+1);
			model.addAttribute("prevPage", reviews.getNumber()-1);
			model.addAttribute("numPage", reviews.getNumber());
			
		}
		String path =  offer.getUser().getId()+"/"+offer.getId();
		try{
			UploadFiles uploadedFiles = new UploadFiles();
			int numberFiles = new File(uploadedFiles.getFilesFolder()+path).listFiles().length;
			
			List<String> namePhotos = new ArrayList();
			for(int i = numberFiles-1; i>=0; i--){
				namePhotos.add(i+".jpg");
			}
			
			model.addAttribute("photos", namePhotos);
			
			return "offer";
		}catch(Exception e){
			return "offer";
		}
	}
	
	@RequestMapping("/addReview/{id}")
	public String addReviewOffer(Model model, @PathVariable long id,@RequestParam("valoration") float valoration, @RequestParam("comment") String comment) {
		if(webService.isLoggedUser()){
			Offer offer = webService.getOfferById(id);
			Review review = new Review(valoration, comment);
			review.setOfferReview(offer);
			review.setUserReview(webService.getLoggedUser());
			webService.saveReview(review);
			return "redirect:/offer/"+offer.getId()+"?page=0&size=4";
		}else{
			return "redirect:/offer/"+id;
		}	
	}
	
	@RequestMapping("/newAd")
	public String newAd() {
		return "newAd";
	}
	
	@RequestMapping("/offerModify/{idOffer}")
	public String offerModify(Model model,@PathVariable long idOffer) {
		
		User user = webService.getLoggedUser();
		Offer offer = webService.getOfferById(idOffer);	
		
		if((user.getId()==offer.getUser().getId()) || (user.getRoles().toString().equals("[ROLE_USER, ROLE_ADMIN]"))){
			model.addAttribute("offer", offer);
			return "adModify";
		}
		else{
			return "signin";
		}
	}
	
	@RequestMapping("/deleteOffer/{idOffer}")
	public String deleteOffer(Model model, @PathVariable long idOffer) {
		
		if(webService.isLoggedUser()){
			Offer offer = webService.getOfferById(idOffer);
			User user = webService.getLoggedUser();
			//User user = userRepository.findOne(userComponent.getLoggedUser().getId());
			if((user.getId() == offer.getUser().getId()) || (user.getRoles().toString().equals("[ROLE_USER, ROLE_ADMIN]"))){
				webService.deleteOffer(idOffer);
				if(user.getRoles().toString().equals("[ROLE_USER, ROLE_ADMIN]")){
					return "redirect:/admin";
				}else{
					model.addAttribute("user", user);
					return "redirect:/user";
				}
			}
			else{
				return "redirect:/signin";
			}
		}
		else{
			return "redirect:/signin";
		}
		
	}
	
	@RequestMapping(value="/setModify/{idOffer}", method=RequestMethod.POST)
	public String setModify(Model model, Offer editOffer, String attributes, @PathVariable long idOffer, @RequestParam("file") List<MultipartFile> files){
		
		if(webService.isLoggedUser()){
			User user = webService.getLoggedUser();
			Offer originalOffer = webService.getOfferById(idOffer);
			webService.deleteCharacteristics(originalOffer.getCharacteristics());
			if((user.getId() == originalOffer.getUser().getId()) || (user.getRoles().toString().equals("[ROLE_USER, ROLE_ADMIN]"))){
				editOffer.setId(idOffer);
				editOffer.setUser(user);
				
				String[] atributtesList= attributes.split(",");
				for(String attribute :atributtesList){
					Characteristics c = new Characteristics(attribute, true);
					c.setOffer(editOffer);

					webService.saveCharacteristics(c);
				}
				
				String path =  user.getId()+"/"+editOffer.getId();
				//String path =  editOffer.getUser().getId()+"/"+editOffer.getId();
				
				UploadFiles uploadedFiles = new UploadFiles();
				uploadedFiles.handleFileUpload(files,path);	
				
				webService.saveOffer(editOffer);
				model.addAttribute("offer", editOffer);
				return "redirect:/offer/"+editOffer.getId()+"/?page=0&size=4";
			}
			else{
				return "redirect:/signin";
			}
		}
		else{
			return "redirect:/signin";
		}
	}
	
	
	@RequestMapping("/newOffer")
	public String newOffer(Model model, Offer offer, String attributes, @RequestParam("file") List<MultipartFile> files) {
	
		User user = webService.getLoggedUser();
		 
		Offer offerSave = webService.saveAndFlushOffer(offer);
		offer.setUser(user);
		
		String[] atributtesList= attributes.split(",");
		for(String attribute :atributtesList){
			Characteristics c = new Characteristics(attribute, true);
			c.setOffer(offer);
			webService.saveCharacteristics(c);
		}
				
		String path =  user.getId()+"/"+offerSave.getId();
		
		UploadFiles uploadedFiles = new UploadFiles();
		uploadedFiles.handleFileUpload(files,path);
		
		model.addAttribute("offer", offer);
		return "redirect:/offer/"+offer.getId()+"?page=0&size=4";
	}
	

}