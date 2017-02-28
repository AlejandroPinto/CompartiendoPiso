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
	
	@PostConstruct
	public void init() {
		//offerRepository.save(new Offer("Pepe", "Hola caracola", "XXXX","","","","","","","","","",""));
		//offerRepository.save(new Offer("Pepe", "Hola caracola", "XXXX","","","","","","","","","",""));
		
	}
	
	/*@RequestMapping("/offer-room/new")
	public String nuevoAnuncio(Model model, Offer offer) {

		offers.add(offer);

		usuario.setNombre(offer.getNombre());
		usuario.incAnuncios();

		return "offer-house";

	}

	@RequestMapping("/offer/new")
	public String newOffer(Model model, Offer offer) {

		offerRepository.save(offer);

		return "anuncio_guardado";

	}*/

	@RequestMapping("/anuncio/{id}")
	public String verAnuncio(Model model, @PathVariable long id) {
		
		Offer offer = offerRepository.findOne(id);

		model.addAttribute("anuncio", offer );

		return "ver_anuncio";
	}
	
	
	

}
