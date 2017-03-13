package es.urjc.code.daw.compartiendoPiso;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.Offer.Characteristics;
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
			@RequestParam int rooms, @RequestParam int area,@RequestParam String attributes){
		
		String[] atributtesList= attributes.split(",");
		String a1 = "";
		String a2 = "";
		String a3 = "";
		String a4 = "";
		String a5 = "";
		String a6 = "";
		String a7 = "";
		String a8 = "";
		String a9 = "";
		String a10 = "";
		String a11 = "";
		String a12 = "";
		
		for(String attribute :atributtesList){
			switch (attribute) {
			case "Aire":
				a1=attribute;
				break;
			case "Ascensor":
				a2=attribute;
				break;
			case "Terraza":
				a3=attribute;
				break;
			case "Piscina":
				a4=attribute;
				break;
			case "Fumadores":
				a5=attribute;
				break;
			case "Mascotas":
				a6=attribute;
				break;
			case "Iluminado":
				a7=attribute;
				break;
			case "Bajo":
				a8=attribute;
				break;
			case "Trastero":
				a9=attribute;
				break;
			case "Calefacción central":
				a10=attribute;
				break;
			case "Patio":
				a11=attribute;
				break;
			case "Garaje":
				a12=attribute;
				break;
			default:
				break;
			}
			System.out.println(attribute);
		}
		System.out.println(" a1:"+a1+" a2:"+a2+" a3:"+a3+" a4:"+a4+" a5:"+a5+" a6:"+a6+" a7:"+a7+" a8:"+a8+" a9:"+a9+" a10:"+a10+" a11:"+a11+" a12:"+a12);
		List<Offer> offers = offerRepository.masterQuery(queryBox,type,priceFrom,priceTo,area,rooms,bathroom);
		
//		List<Offer> verifyOffers = new ArrayList<>();
//		for(Offer o :offers){
//				Collection<String> listOne = atributtesList; 
//				verifyOffers.add(o);
//		}
		System.out.println(verifyOffers);
		model.addAttribute("offers",offers);
		if(offers.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else{
			return new ResponseEntity<List<Offer>>(offers,HttpStatus.OK);
		}	
	}
	
}
