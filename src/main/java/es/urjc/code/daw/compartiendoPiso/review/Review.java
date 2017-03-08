package es.urjc.code.daw.compartiendoPiso.review;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	
	private float valoration;
	private String comment;
	private Date date;
	
	@ManyToOne
	private Offer offerReview;
	
	@ManyToOne
	private User userReview;
	
	public Review(){
	}
	
	public Review(float valoration, String comment) {
		super();
		this.valoration = valoration;
		this.comment = comment;
		this.date = new Date();
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public float getValoration() {
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

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy hh:mm");
		return dateFormat.format(this.date);
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
