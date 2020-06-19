package com.olga.couponsPhase3.entyties;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Companies")

public class Company implements Serializable {
	// ---------------properties---------------
	@Id
	@GeneratedValue
	@Column(name = "COMPANY_ID")
	private Long companyId;

	@Column(name = "NAME", unique = true, nullable = false)
	private String name;

	@Column(name = "CONTACT_EMAIL", nullable = false)
	private String contactEmail;
	
	@Column(name = "ADRESS", nullable = false)
	private String adress;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Coupon> coupons;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<User> users;

	// ---------------setters and getters---------------

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setId(long id) {
		this.companyId = id;
	}

	public void setName(String name) {

		this.name = name;
	}
	// ---------------constructor---------------

//	public Company(String name, String contactEmail, String adress) {
//
//		setName(name);
//		setAdress(adress);
//		setContactEmail(contactEmail);
//	}

//	public Company(String name, String contactEmail, String adress, long id) {
//		super();
//		this.name = name;
//		setAdress(adress);
//		setContactEmail(contactEmail);
//		this.id = id;
//	}

	public Company() {
		super();
	}

	// ---------------to string-----------------
	public String toString() {
		return "company id: " + companyId + "\nname: " + name + "\ncontact email: " + contactEmail + "\nadress: " + adress
				+ "\n";
	}

}
