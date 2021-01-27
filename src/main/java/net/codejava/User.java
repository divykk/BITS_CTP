package net.codejava;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String first_name;
	
	private String last_name;
	
	@Column(unique=true)
	private String mobile;
	
	@OneToMany(fetch=FetchType.EAGER,mappedBy="user")
	private List<Listing> listing;
	
	@Column(nullable=false, unique=true)
	private String username; //in login form & register form make constraint of type=email only 
	
	private String password;
	private String address;
	private String role="ROLE_USER";
	private boolean enabled=true;

	public User() {
		
	}
	
	public User(String first_name, String last_name, String mobile, String username, String password, String address,
			String role) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.address = address;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public List<Listing> getListing() {
		return listing;
	}

	public void setListing(List<Listing> listing) {
		this.listing = listing;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", first_name=" + first_name + ", last_name=" + last_name + ", mobile=" + mobile
				+ ", listing=" + listing + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", role=" + role + ", enabled=" + enabled + "]";
	}

}
