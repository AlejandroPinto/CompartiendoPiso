package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.Collection;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.UploadFiles;
import es.urjc.code.daw.compartiendoPiso.WebService;
import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;
import es.urjc.code.daw.compartiendoPiso.review.Review;


@RestController
@RequestMapping("/api/offer")
public class OfferRestController {
	
	@Autowired
	private WebService service;
	
	interface CompleteOffer extends Offer.BasicOffer, User.BasicUser, Review.BasicReview, Characteristics.BasicCharacteristics{}
	
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
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value = "/addOffer", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Offer> addOffer(@RequestBody Offer offer ){
		if(service.isLoggedUser()){
			User user = service.getLoggedUser();
			offer.setUser(user);
			service.saveOffer(offer);
			return new ResponseEntity<Offer>(offer, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value = "/setOfferPhoto/{id}", method = RequestMethod.PUT, consumes = "multipart/form-data")
	public ResponseEntity<Offer> setPhotoToOffer(@PathVariable long id, @RequestParam("file") List<MultipartFile> files ){
		if((service.isLoggedUser()) && (service.getOfferById(id).getUser().getId() == service.getUserId())){
			Offer updateOffer = service.getOfferById(id);
			UploadFiles uploadFiles = new UploadFiles();
			String path =  service.getLoggedUser().getId()+"/"+id;
			uploadFiles.handleFileUpload(files,path);
				return new ResponseEntity<Offer>(updateOffer, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Offer> updateOffer(@PathVariable long id, @RequestBody Offer updatedOffer) {
		
		Offer offer = service.getOfferById(id);
		if (offer != null) {
			updatedOffer.setId(id);
			service.saveOffer(updatedOffer);
			return new ResponseEntity<Offer>(offer, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
		service.deleteOffer(id);
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
