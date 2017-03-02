package es.urjc.code.daw.compartiendoPiso.Offer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Characteristics {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private long id;
	 private String name;
	 private boolean value;
	 @ManyToOne
	 private Offer offer;
	 
	 protected Characteristics(){}
	 
	 public Characteristics(String name, boolean value){
		 super();
		 this.name=name;
		 this.value=value;
	 }

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Offer getOffer() {
		return offer;
	}

	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isValue() {
		return value;
	}

	public void setValue(boolean value) {
		this.value = value;
	}
	 
}
