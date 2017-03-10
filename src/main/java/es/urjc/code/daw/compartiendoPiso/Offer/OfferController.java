package es.urjc.code.daw.compartiendoPiso.Offer;

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

import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;
import es.urjc.code.daw.compartiendoPiso.User.UserRepository;
import es.urjc.code.daw.compartiendoPiso.review.Review;
import es.urjc.code.daw.compartiendoPiso.review.ReviewRepository;


@Controller
public class OfferController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CharacteristicsRepository characteristicsRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserComponent userComponent;
	
	
	@PostConstruct
	public void init() {
		
		User user = userRepository.saveAndFlush(new User ("JUAN","Sanchez","Sanchez","c@c.com",918115789,"1234","Soy una maquina",false,"ROLE_USER"));
		
		Offer offer = new Offer("Chale","Chalé bastante moderno",222,"Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.","Madrid","Navalcarnero","el olivar",200,2,1,1);
		offer.setUser(user);
		offerRepository.save(offer);
		Characteristics c1 = new Characteristics("Terraza", true);
		Characteristics c2 = new Characteristics("Balcón", true);
		Characteristics c3 = new Characteristics("Alarma", true);
		c1.setOffer(offer);
		c2.setOffer(offer);
		c3.setOffer(offer);
		characteristicsRepository.save(c1);
		characteristicsRepository.save(c2);
		characteristicsRepository.save(c3);	
		
		
		
		User user2 = userRepository.saveAndFlush(new User ("JUAN","Sanchez","Sanchez","d@d.com",918115789,"1234","Soy una maquina",false,"ROLE_USER"));
		for(int i=0;i<100;i++){
			Review review = new Review(5,"regular"+i);
			review.setOfferReview(offer);
			review.setUserReview(user2);
			reviewRepository.save(review);
		}
		
	}

	@RequestMapping("/offer/{id}")
	public String verAnuncio(Model model, @PathVariable long id, Pageable pageable, @RequestParam int page, @RequestParam int size) {
		if(userComponent.isLoggedUser()){
			model.addAttribute("isLogued",true);
		}
		Offer offer = offerRepository.findOne(id);		
		model.addAttribute("offer", offer);
		Page<Review> reviews = reviewRepository.findByOfferReview(offer, new PageRequest(page,size));
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
		
		return "offer";
	}
	
	@RequestMapping("/addReview/{id}")
	public String addReviewOffer(Model model, @PathVariable long id,@RequestParam("valoration") float valoration, @RequestParam("comment") String comment) {
		if(userComponent.isLoggedUser()){
			Offer offer = offerRepository.findOne(id);
			Review review = new Review(valoration, comment);
			review.setOfferReview(offer);
			review.setUserReview(userComponent.getLoggedUser());
			reviewRepository.save(review);
			return "redirect:/offer/"+offer.getId();
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
		
		User user = userComponent.getLoggedUser();
		Offer offer = offerRepository.findOne(idOffer);	
		
		if((user.getId()==offer.getUser().getId()) || (user.getRoles().toString().equals("[ROLE_ADMIN]"))){
			model.addAttribute("offer", offer);
			return "adModify";
		}
		else{
			return "signin";
		}
	}
	
	@RequestMapping("/deleteOffer/{idOffer}")
	public String deleteOffer(Model model, @PathVariable long idOffer) {
		
		if(userComponent.isLoggedUser()){
			Offer offer = offerRepository.findOne(idOffer);
			User user = userComponent.getLoggedUser();
			//User user = userRepository.findOne(userComponent.getLoggedUser().getId());
			System.out.println();
			if((user.getId() == offer.getUser().getId()) || (user.getRoles().toString().equals("[ROLE_ADMIN]"))){
				characteristicsRepository.delete(offer.getCharacteristics());
				reviewRepository.delete(offer.getReviews());
				offerRepository.delete(idOffer);
				if(user.getRoles().toString().equals("[ROLE_ADMIN]")){
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
	public String setModify(Model model, Offer editOffer, String attributes, @PathVariable long idOffer){
		
		if(userComponent.isLoggedUser()){
			User user = userComponent.getLoggedUser();
			Offer originalOffer = offerRepository.findOne(idOffer);
			characteristicsRepository.delete(originalOffer.getCharacteristics());
			if((user.getId() == originalOffer.getUser().getId()) || (user.getRoles().toString().equals("[ROLE_ADMIN]"))){
				editOffer.setId(idOffer);
				editOffer.setUser(user);
				
				String[] atributtesList= attributes.split(",");
				for(String attribute :atributtesList){
					Characteristics c = new Characteristics(attribute, true);
					c.setOffer(editOffer);

					characteristicsRepository.save(c);
				}
				offerRepository.save(editOffer);
				model.addAttribute("offer", editOffer);
				return "redirect:/offer/"+editOffer.getId();
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
	public String newOffer(Model model, Offer offer, String attributes) {
	
		User user = userComponent.getLoggedUser();
		 
		offerRepository.save(offer);
		offer.setUser(user);
		
		String[] atributtesList= attributes.split(",");
		for(String attribute :atributtesList){
			Characteristics c = new Characteristics(attribute, true);
			c.setOffer(offer);
			characteristicsRepository.save(c);
		}
		
		model.addAttribute("offer", offer);
		return "redirect:/offer/"+offer.getId()+"?page=0&size=4";
	}
	
	
	

}