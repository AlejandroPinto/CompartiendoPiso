package es.urjc.code.daw.compartiendoPiso.review;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.User.User;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private int valoration;
	private String comment;
	
	@ManyToOne
	private Offer offerReview;
	
	@ManyToOne
	private User userReview;
	
	
	public Review(int valoration, String comment) {
		super();
		this.valoration = valoration;
		this.comment = comment;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public int getValoration() {
		return valoration;
	}
	
	public void setValoration(int valoration) {
		this.valoration = valoration;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Offer getOfferReview() {
		return offerReview;
	}

	public void setOfferReview(Offer offerReview) {
		this.offerReview = offerReview;
	}

	public User getUserReview() {
		return userReview;
	}

	public void setUserReview(User userReview) {
		this.userReview = userReview;
	}
	
	
}
