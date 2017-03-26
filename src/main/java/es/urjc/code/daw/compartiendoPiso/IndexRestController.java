package es.urjc.code.daw.compartiendoPiso;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.Offer.OfferRepository;




@RestController
public class IndexRestController {
	
	@Autowired
	private OfferRepository offerRepository;
	interface CompleteOffer extends Offer.BasicOffer, User.BasicUser{}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value="/search", method=RequestMethod.GET)
	public ResponseEntity<List<Offer>> indexView(Model model,@RequestParam String queryBox,
			@RequestParam float priceTo, @RequestParam float priceFrom,
			@RequestParam String type, @RequestParam int bathroom,
			@RequestParam int rooms, @RequestParam int area,@RequestParam int page){
		Page<Offer> offersPage = offerRepository.masterQuery(queryBox,type,priceFrom,priceTo,area,rooms,bathroom,new PageRequest(page,10));
		List<Offer> offers = offersPage.getContent();

		model.addAttribute("offers",offers);
		if(!offersPage.hasContent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Offer>>(offers,HttpStatus.OK);
		}	
	}
	
}
