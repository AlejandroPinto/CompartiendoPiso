package es.urjc.code.daw.compartiendoPiso.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.Offer.Characteristics;
import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.User.User;

@RestController
@RequestMapping("/api")
public class ReviewRestController {
	
	@Autowired
	private WebService service;
	
	interface CompleteReview extends Offer.BasicOffer, User.BasicUser, Review.BasicReview, Characteristics.BasicCharacteristics{};
	
	@JsonView(CompleteReview.class)
	@RequestMapping(value = "/addReview/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Offer> addReview(@PathVariable long id,@RequestBody Review review){
		if(service.isLoggedUser()){
			Offer offer = service.getOfferById(id);
			Review newReview = service.saveAndFlushReview(review);
			newReview.setOfferReview(offer);
			newReview.setUserReview(service.getLoggedUser());
			service.saveReview(newReview);
			
			System.out.println(offer.getReviews().toString());
			return new ResponseEntity<Offer>(offer, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
