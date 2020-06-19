package com.olga.couponsPhase3.entyties;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Customers")

public class Customer implements Serializable{

	//---------------properties---------------
	@Id
	@Column(name = "CUSTOMER_ID")
	private long customerId;
	
	@Column(name = "first_Name", nullable = false)
	private String firstName;
	
	@Column(name = "second_Name", nullable = false)
	private String secondName;

	@JoinColumn
	@MapsId
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.EAGER)
	private User user;
	
	
	//---------------setters and getters---------------
	
	public String getFirstName() {
		return firstName;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setId(long id) {
		this.customerId = id;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getSecondName() {
		return secondName;
	}
	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	//---------------constructor---------------
	/**
	 * constructor to add info
	 * @param email
	 * @param password
	 * @param firstName
	 * @param secondName
	 * @throws InvalidEmailException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Customer(long id, String firstName, String secondName, User user) {
		setId(id);
		setFirstName(firstName);
		setSecondName(secondName);
		setUser(user);
	}
	public Customer(long id, String firstName, String secondName) {
		setId(id);
		setFirstName(firstName);
		setSecondName(secondName);

	}

	public Customer() {
		super();
	}
	//---------------to string---------------
	public String toString(){
		return "customer id: "+customerId+"\nfirst name: "+firstName
				+"\nsecond name: "+secondName+"\n";
	}
}
