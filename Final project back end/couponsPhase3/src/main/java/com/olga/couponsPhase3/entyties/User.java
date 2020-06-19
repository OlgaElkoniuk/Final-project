package com.olga.couponsPhase3.entyties;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.olga.couponsPhase3.enums.ClientType;

@Entity
@Table(name = "Users")

public class User implements Serializable{

	// ---------------properties------------------

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private long userId;
	
	@Column(name = "EMAIL", unique = true, nullable = false)
	private String email;

	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CLIENT_TYPE", nullable = false)
	private ClientType clientType;
	
	
	
	@ManyToOne
	private Company company;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Purchase> purchases;

//---------------setters and getters------------------
	
	
	public void setId(long id) {
		this.userId = id;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {

		this.password = password;

	}

	public long getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public ClientType getClientType() {

		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String clientTypeToString(ClientType clientType) {

		return clientType.name();
	}
//-------------constructor----------------


	public User(long id, String email, String password, ClientType clientType, Long company_id) {
		super();
		setClientType(clientType);
		setId(id);
		setEmail(email);
		setPassword(password);
		
	}


	public User(String email, String password, ClientType clientType, Long company_id) {
		super();
		setClientType(clientType);
		setEmail(email);
		setPassword(password);

	}

	public User() {
		super();
	}

public User(long customerId) {
	setId(customerId);
	}

	//---------------to string-----------------
	public String toString() {
		return "user id: " + userId + "\nuser name: " + email + "\npassword: " + password + "\ncompany id: " + this.company.getCompanyId()
				+ "\n";
	}
}
