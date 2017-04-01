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
import es.urjc.code.daw.compartiendoPiso.review.Review;
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
		Offer o= offerRepository.saveAndFlush(offer);
		for(Characteristics c :offer.getCharacteristics()){
			c.setOffer(o);
			characteristicsRepository.save(c);
		}
	}	
	
	public User getOfferByUser(long id){
		return userRepository.findOne(id);
	}
	
	public void deleteOffer(long id) {
		offerRepository.delete(id);
	}
	
	public Review saveAndFlushReview(Review review){
		return reviewRepository.saveAndFlush(review);
		
	}
	
	public void saveReview(Review review){
		reviewRepository.save(review);
	}
	
	//USER UTILS
	public void saveUser(User user){
		userRepository.save(user);
	}
	
	public User getUserById(long id){
		return userRepository.findOne(id);
	}
	
	public boolean isLoggedUser(){
		return userComponent.isLoggedUser();
	}
	
	public User getLoggedUser(){
		return userComponent.getLoggedUser();
	}
	
	public boolean isAdmin(){
		return userComponent.getLoggedUser().getRoles().equals("[ROLE_ADMIN]");
	}
	
	public long getUserId(){
		return userComponent.getLoggedUser().getId();
	}
	public void deleteUser(long id){
		userRepository.delete(id);
	}
	public UserRepository getUserRepository(){
		return this.userRepository;
	}
}
