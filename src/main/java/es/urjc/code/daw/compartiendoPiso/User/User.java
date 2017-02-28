package es.urjc.code.daw.compartiendoPiso.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	private String firstLastName;
	private String secondLastName;
	private String email;
	private int phone;
	
	@Column(length = 1080)
	private String description;
	
	private String pass;
	private boolean admin;	
	
	public User () {}
	
	public User(String name, String firstLastName, String secondLastName, String email, int phone, String pass, String description,
			boolean admin) {
		super();
		this.name = name;
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.email = email;
		this.phone = phone;
		this.pass = new BCryptPasswordEncoder().encode(pass);
		this.description = description;
		this.admin = admin;
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
	
	
	
	
}
