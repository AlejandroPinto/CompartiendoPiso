package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import es.urjc.code.daw.compartiendoPiso.User.User;
import es.urjc.code.daw.compartiendoPiso.review.Review;


@Entity
public class Offer {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 private String title;
	 private float price;
	 private String description;
	 private String province;
	 private String location;
	 private String neighborhood;
	 private int area;
	 private int bathroom;
	 //private String[] photos;
	 private int rooms;
	 private String type;
	 //@OneToMany
	 //private List<User> members = new ArrayList<>();
	 
	 @ManyToOne
	 private User user;
	 
	 @OneToMany(mappedBy="offerReview")
	 private List<Review> reviews = new ArrayList<>();
	
	 public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToMany(mappedBy="offer")
	 private List<Characteristics> characteristics = new ArrayList<>();
	 
	 //private User usesrID;
	 private int numPlaces;
	 
	 public int getBathroom() {
		return bathroom;
	}

	public void setCharacteristics(List<Characteristics> characteristics) {
		this.characteristics = characteristics;
	}

	protected Offer() {}
	
	 public Offer(String type, String title, float price, String description, String province, String location,
	 String neighborhood, int area, int bathdroom, int rooms,
	 int numPlaces) {
		this.title = title;
		this.price = price;
		this.description = description;
		this.province = province;
		this.location = location;
		this.neighborhood = neighborhood;
		this.area = area;
		this.bathroom = bathdroom;
		this.rooms = rooms;
		this.type = type;
		this.numPlaces = numPlaces;
	}
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String tittle) {
		this.title = tittle;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getBathdroom() {
		return bathroom;
	}
	public void setBathroom(int bathdroom) {
		this.bathroom = bathdroom;
	}
	public int getRooms() {
		return rooms;
	}
	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumPlaces() {
		return numPlaces;
	}
	public void setNumPlaces(int numPlaces) {
		this.numPlaces = numPlaces;
	}

	public List<Characteristics> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(ArrayList<Characteristics> characteristics) {
		this.characteristics = characteristics;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", title=" + title + ", price=" + price + ", description=" + description
				+ ", province=" + province + ", location=" + location + ", neighborhood=" + neighborhood + ", area="
				+ area + ", bathdroom=" + bathroom + ", rooms=" + rooms + ", type=" + type + ", characteristics="
				+ characteristics + ", numPlaces=" + numPlaces + "]";
	}

	 
	 
	 
	 

}
