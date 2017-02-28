package es.urjc.code.daw.compartiendoPiso.Offer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class OfferController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private CharacteristicsRepository characteristicsRepository;
	
	@PostConstruct
	public void init() {
		
		Offer offer = new Offer("Oferta1",222,"casa bonita","Madrid","location","el olivar",200,2,1,"chalé",1);
		//offerRepository.save(new Offer("Oferta1",222,"casa bonita","Madrid","location","el olivar",200,2,1,"chalé",1));
		offerRepository.save(offer);
		Characteristics c1 = new Characteristics("Terraza", true);
		c1.setOffer(offer);
		characteristicsRepository.save(c1);
		//offerRepository.save(offer);
		
	}

	/*@RequestMapping("/offer/new")
	public String nuevoAnuncio(Model model, Offer offer) {
		offers.add(offer);
		usuario.setNombre(offer.getNombre());
		usuario.incAnuncios();
		return "offer-house";
	}*/

	/*@RequestMapping("/offer/new")
	public String newOffer(Model model, Offer offer) {

		offerRepository.save(offer);

		return "anuncio_guardado";

	}*/

	@RequestMapping("/offer/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Offer offer = offerRepository.findOne(id);

		model.addAttribute("offer", offer);

		return "offer";
	}
	
	
	

}
