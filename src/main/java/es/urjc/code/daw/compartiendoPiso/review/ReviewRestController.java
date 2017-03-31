package es.urjc.code.daw.compartiendoPiso.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.User.User;

public class ReviewRestController {
	
	@Autowired
	private WebService service;
	
	interface CompleteReview extends  User.BasicUser, Review.BasicReview{};
	
	//@JsonView(CompleteReview.class)
	//@RequestMapping(value = "/addReview/{id}", method = RequestMethod.POST)
	//@ResponseStatus(HttpStatus.CREATED)
	//public ResponseEntity<Offer> addReview(@RequestParam long id,@RequestBody Review review){
//		if(service.isLoggedUser()){
//			Offer offer = service.getOfferById(id);
//			Review review = new Review(valoration, comment);
//			review.setOfferReview(offer);
//			review.setUserReview(userComponent.getLoggedUser());
//			reviewRepository.save(review);
//			return new ResponseEntity<Offer>(offer, HttpStatus.OK);
//		}
//		else{
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
		
//		if(userComponent.isLoggedUser()){
//			Offer offer = offerRepository.findOne(id);
//			Review review = new Review(valoration, comment);
//			review.setOfferReview(offer);
//			review.setUserReview(userComponent.getLoggedUser());
//			reviewRepository.save(review);
//			return "redirect:/offer/"+offer.getId()+"?page=0&size=4";
//		}else{
//			return "redirect:/offer/"+id;
//		}	
	//}

}
