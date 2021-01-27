package net.codejava;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import net.codejava.Category;

@Entity
@Table(name="listings")
public class Listing {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int listing_id;
	
	@Column(nullable=false)
	private String listing_name;
	
	@CreationTimestamp
	private LocalDateTime dateUploaded;
	@Column(nullable=false)
	private String decription;
	
	@Column(nullable=false)
	private int price;
	
	@Column(nullable=false)
	private boolean availability;
	
	@Lob
	private byte[] image_1;
	@Lob
	private byte[] image_2;
	@Lob
	private byte[] image_3;
	@Lob
	private byte[] image_4;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
	@JoinColumn(name="category_id")
	private Category category;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="username")
	private User user;
	
	public Listing() {
		
	}
	public Listing(String listing_name, LocalDateTime dateUploaded, String decription, int price, boolean availability,
			byte[] image_1, byte[] image_2, byte[] image_3, byte[] image_4, Category category) {
		this.listing_name = listing_name;
		this.dateUploaded = dateUploaded;
		this.decription = decription;
		this.price = price;
		this.availability = availability;
		this.image_1 = image_1;
		this.image_2 = image_2;
		this.image_3 = image_3;
		this.image_4 = image_4;
		this.category = category;
	}
	public int getListing_id() {
		return listing_id;
	}

	public void setListing_id(int listing_id) {
		this.listing_id = listing_id;
	}

	public String getListing_name() {
		return listing_name;
	}

	public void setListing_name(String listing_name) {
		this.listing_name = listing_name;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public byte[] getImage_1() {
		return image_1;
	}

	public void setImage_1(byte[] image_1) {
		this.image_1 = image_1;
	}

	public byte[] getImage_2() {
		return image_2;
	}

	public void setImage_2(byte[] image_2) {
		this.image_2 = image_2;
	}

	public byte[] getImage_3() {
		return image_3;
	}

	public void setImage_3(byte[] image_3) {
		this.image_3 = image_3;
	}

	public byte[] getImage_4() {
		return image_4;
	}

	public void setImage_4(byte[] image_4) {
		this.image_4 = image_4;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public LocalDateTime getDateUploaded() {
		return dateUploaded;
	}

	public void setDateUploaded(LocalDateTime dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	@Override
	public String toString() {
		return "Listing [listing_id=" + listing_id + ", listing_name=" + listing_name + ", dateUploaded=" + dateUploaded
				+ ", decription=" + decription + ", price=" + price + ", availability=" + availability + ", category="
				+ category + ", user=" + user + "]";
	}
	
}
