package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import es.urjc.code.daw.compartiendoPiso.User.User;


@Controller
public class OfferController {
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private CharacteristicsRepository characteristicsRepository;
	
	@PostConstruct
	public void init() {
		
		Offer offer = new Offer("Chale","Chalé bastante moderno",222,"Vivienda de dos dormitorios, REFORMADA TOTAL (2.013) PARA ENTRAR VIVIR, cocina amueblada ampliada, suelos de gres, carpintería blanco con doble acristalamiento persianas isotérmicas, terraza.","Madrid","Navalcarnero","el olivar",200,2,1,1);
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
	@RequestMapping("/newOffer")
	public String newOffer(Model model, Offer offer, String attributes) {
		Offer saveOffer = offerRepository.saveAndFlush(offer);	
		String[] atributtesList= attributes.split(",");
		for(String attribute :atributtesList){
			Characteristics c = new Characteristics(attribute, true);
			c.setOffer(offer);
			characteristicsRepository.save(c);
		}
		model.addAttribute("offer", saveOffer);
		return "redirect:/offer/"+saveOffer.getId();
	}
	
	
	

}
