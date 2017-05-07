package es.urjc.code.daw.compartiendoPiso.Offer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@RequestMapping(value="/", method=RequestMethod.GET)
	public ResponseEntity <List<Offer>> getAllOffers(){
			return new ResponseEntity<>(service.findAllOffers(), HttpStatus.OK);
	}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Offer> addOffer(@RequestBody Offer offer ){
		if(service.isLoggedUser()){
			User user = service.getUserById(service.getUserId());
			offer.setUser(user);
			service.saveOffer(offer);
			return new ResponseEntity<Offer>(offer, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CompleteOffer.class)
	@RequestMapping(value = "/offerPhoto/{id}", method = RequestMethod.PUT, consumes = "multipart/form-data")
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
		service.deleteCharacteristics(offer.getCharacteristics());
		if (offer != null) {
			updatedOffer.setId(id);
			updatedOffer.setUser(offer.getUser());
						
			for(Characteristics c :updatedOffer.getCharacteristics()){
				c.setOffer(updatedOffer);
				service.saveCharacteristic(c);
			}
			
			service.saveOffer(updatedOffer);
			return new ResponseEntity<Offer>(updatedOffer, HttpStatus.OK);
		}
		else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@JsonView(CompleteOffer.class) 
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Offer> deleteOffer(@PathVariable long id) {
		if(service.isLoggedUser() && (service.getOfferById(id).getUser().getId() == service.getUserId() || service.getUserById(id).getRoles().toString().equals("[ROLE_USER]"))) {	
			service.deleteOffer(id);
			return new ResponseEntity<>(null, HttpStatus.OK); 
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}

	}
	
	@RequestMapping(value = "/imageOffer/{id}", method = RequestMethod.GET)
	public void getImage(@PathVariable long id, HttpServletResponse response) throws FileNotFoundException, IOException{
		User ownOffer = service.getOwnOffer(id);
		IOUtils.copy(new FileInputStream("img\\users\\"+ownOffer.getId()+"\\"+id+"\\0.jpg"), response.getOutputStream());
	}
	

}
