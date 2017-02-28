package es.urjc.code.daw.compartiendoPiso.Offer;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Offer {
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 private String tittle;
	 private float price;
	 private String description;
	 private String province;
	 private String location;
	 private String neighborhood;
	 private int area;
	 private int bathdroom;
	 private String[] photos;
	 private int rooms;
	 private String type;
	 //@OneToMany
	 //private List<User> members = new ArrayList<>();
	
	 @OneToMany
	 private Characteristics characteristics;
	 //private User userID;
	 private int numPlaces;
	 
	 protected Offer() {}
	
	 public Offer(long id, String tittle, float price, String description, String province, String location,
	 String neighborhood, int area, int bathdroom, String[] photos, int rooms, String type,
	 Characteristics characteristics, int numPlaces) {
		super();
		this.id = id;
		this.tittle = tittle;
		this.price = price;
		this.description = description;
		this.province = province;
		this.location = location;
		this.neighborhood = neighborhood;
		this.area = area;
		this.bathdroom = bathdroom;
		this.photos = photos;
		this.rooms = rooms;
		this.type = type;
		this.characteristics = characteristics;
		this.numPlaces = numPlaces;
	}
	 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTittle() {
		return tittle;
	}
	public void setTittle(String tittle) {
		this.tittle = tittle;
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
		return bathdroom;
	}
	public void setBathdroom(int bathdroom) {
		this.bathdroom = bathdroom;
	}
	public String[] getPhotos() {
		return photos;
	}
	public void setPhotos(String[] photos) {
		this.photos = photos;
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
	public Characteristics getCharacteristics() {
		return characteristics;
	}
	public void setCharacteristics(Characteristics characteristics) {
		this.characteristics = characteristics;
	}
	public int getNumPlaces() {
		return numPlaces;
	}
	public void setNumPlaces(int numPlaces) {
		this.numPlaces = numPlaces;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", tittle=" + tittle + ", price=" + price + ", description=" + description
				+ ", province=" + province + ", location=" + location + ", neighborhood=" + neighborhood + ", area="
				+ area + ", bathdroom=" + bathdroom + ", photos=" + Arrays.toString(photos) + ", rooms=" + rooms
				+ ", type=" + type + ", characteristics=" + characteristics + ", numPlaces=" + numPlaces + "]";
	}
	 
	 
	 
	 

}
