package es.urjc.code.daw.compartiendoPiso.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import es.urjc.code.daw.compartiendoPiso.Offer.Offer;
import es.urjc.code.daw.compartiendoPiso.review.Review;

@Entity
public class User {
	
	public interface BasicUser{}
	public interface UserLogin{}
	public interface userAndOffer{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(BasicUser.class)
	private long id;
	@JsonView({BasicUser.class,UserLogin.class,userAndOffer.class})
	private String name;
	@JsonView({BasicUser.class,UserLogin.class,userAndOffer.class})
	private String firstLastName;
	@JsonView({BasicUser.class,UserLogin.class,userAndOffer.class})
	private String secondLastName;
	@JsonView({BasicUser.class,UserLogin.class,userAndOffer.class})
	private String email;
	@JsonView({BasicUser.class,userAndOffer.class})
	private int phone;
	@Column(length = 1080)
	@JsonView({BasicUser.class,userAndOffer.class})
	private String description;
	@JsonIgnore
	private String pass;
	@JsonIgnore
	private boolean admin;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	@JsonIgnore
	private List<String> roles;
	
	public User () {
		this.roles = new ArrayList<>(Arrays.asList("ROLE_USER"));
	}
	@OneToMany(mappedBy="user")
	@JsonView(userAndOffer.class)
	private List<Offer> offers = new ArrayList<>();
	
	@OneToMany(mappedBy="userReview")
	private List<Review> reviews = new ArrayList<>();

	
	public User(String name, String firstLastName, String secondLastName, String email, int phone, String pass, String description,
			boolean admin, String... roles) {
		super();
		this.name = name;
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.email = email;
		this.phone = phone;
		this.pass = new BCryptPasswordEncoder().encode(pass);
		this.description = description;
		this.admin = admin;
		this.roles = new ArrayList<>(Arrays.asList(roles));
	}
	
	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffer(Offer offer) {
		this.offers.add(offer);
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFirstLastName() {
		return firstLastName;
	}
	
	public void setFirstLastName(String firstLastName) {
		this.firstLastName = firstLastName;
	}
	
	public String getSecondLastName() {
		return secondLastName;
	}
	
	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPhone() {
		return phone;
	}
	
	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
	
	
}