package es.urjc.code.daw.compartiendoPiso.Offer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.urjc.code.daw.compartiendoPiso.UploadFiles;
import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.User.UserComponent;
import es.urjc.code.daw.compartiendoPiso.User.UserRepository;
import es.urjc.code.daw.compartiendoPiso.review.Review;
import es.urjc.code.daw.compartiendoPiso.review.ReviewRepository;


@RestController
public class OfferRestController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CharacteristicsRepository characteristicsRepository;
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private OfferRepository offerRepository;
	
	@Autowired
	private UserComponent userComponent;

	@RequestMapping("/reviews/{{idoffer}}")
	public ResponseEntity<Page<Review>> verAnuncio(@PathVariable long idoffer, Pageable pageable, @RequestParam int page, @RequestParam int size) {
		Offer offer = offerRepository.findOne(idoffer);
		Page<Review> reviews = reviewRepository.findByOfferReview(offer,new PageRequest(page,size));
//		
//		model.addAttribute("reviews", reviews);
//		model.addAttribute("numReviews",offer.getReviews().size());
//		
//		model.addAttribute("showNext", !reviews.isLast());
//		model.addAttribute("showPrev", !reviews.isFirst());
//		for(int i=1; i<=5; i++){
//			if(!reviews.isLast()){
//				model.addAttribute("nextPage"+i, reviews.getNumber()+i);
//			}
//			if(!reviews.isFirst()){
//				model.addAttribute("nextPage"+i, reviews.getNumber()+i);
//			}
//			model.addAttribute("nextPage", reviews.getNumber()+1);
//			model.addAttribute("prevPage", reviews.getNumber()-1);
//			model.addAttribute("numPage", reviews.getNumber());
//			
//		}
//		String path =  offer.getUser().getId()+"/"+offer.getId();
//		
//		UploadFiles uploadedFiles = new UploadFiles();
//		int numberFiles = new File(uploadedFiles.getFilesFolder()+path).listFiles().length;
//		
//		List<String> namePhotos = new ArrayList();
//		for(int i = numberFiles-1; i>=0; i--){
//			namePhotos.add(i+".jpg");
//		}
//		System.out.println(namePhotos.get(0));
//		
//		model.addAttribute("photos", namePhotos);
		
		return  new ResponseEntity<Page<Review>>(reviews,HttpStatus.OK);
	}
}