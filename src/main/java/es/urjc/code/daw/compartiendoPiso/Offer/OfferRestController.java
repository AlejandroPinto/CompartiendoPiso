package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.review.Review;


@RestController
@RequestMapping("/api/offer")
public class OfferRestController {
	
	@Autowired
	private WebService service;
	
	interface CompleteOffer extends Offer.BasicOffer, User.BasicUser, Review.BasicReview{}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Offer> getOffer(@PathVariable long id){
		Offer offer = service.getOfferById(id);
		if(offer != null){
			return new ResponseEntity<Offer>(offer, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/addOffer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Offer addOffer(@RequestBody Offer offer){
		service.saveOffer(offer);
		return offer;
	}
	
}
