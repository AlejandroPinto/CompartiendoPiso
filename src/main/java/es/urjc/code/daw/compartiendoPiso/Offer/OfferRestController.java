package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.IndexRestController.CompleteOffer;
import es.urjc.code.daw.compartiendoPiso.review.Review;
import es.urjc.code.daw.compartiendoPiso.review.ReviewRepository;


@RestController
public class OfferRestController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	interface CompleteReview extends Review.BasicReview, CompleteOffer{}
	
	@JsonView(CompleteReview.class)
	@RequestMapping(value="/reviews", method=RequestMethod.GET)
	public ResponseEntity<HashMap> verAnuncio(@RequestParam long idoffer, Pageable pageable, @RequestParam int page, @RequestParam int size) {
		Offer offer = offerRepository.findOne(idoffer);
		Page<Review> reviews = reviewRepository.findByOfferReview(offer,new PageRequest(page,size));
		HashMap<String,Object> reviewsList = new HashMap<>();
		reviewsList.put("content", reviews.getContent());
		reviewsList.put("numPage",reviews.getNumber());
		if(reviews.hasNext()){
			reviewsList.put("nextPage",reviews.getNumber()+1);
		}
		if(reviews.hasPrevious()){
			reviewsList.put("prevPage",reviews.getNumber()-1);
		}
		reviewsList.put("numpages", reviews.getTotalPages());
		
		return  new ResponseEntity<HashMap>(reviewsList,HttpStatus.OK);
	}
}