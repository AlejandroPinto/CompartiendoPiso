package es.urjc.code.daw.compartiendoPiso;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.code.daw.compartiendoPiso.Offer.Characteristics;
import es.urjc.code.daw.compartiendoPiso.Offer.CharacteristicsRepository;
import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.Offer.OfferRepository;
import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;
import es.urjc.code.daw.compartiendoPiso.User.UserRepository;
import es.urjc.code.daw.compartiendoPiso.review.ReviewRepository;

@Service
public class WebService {
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
	
	
	public Offer getOfferById(long id){
		return offerRepository.findOne(id);	
	}

	public void saveOffer(Offer offer) {
				offerRepository.save(offer);
	}	
	
	public void saveCharacteristics(Characteristics characteristics) {
		characteristicsRepository.save(characteristics);
	}
	
	public User getOfferByUser(long id){
		return userRepository.findOne(id);
	}
	
	public void deleteOffer(long id) {
		offerRepository.delete(id);
	}
	
	
	
}
