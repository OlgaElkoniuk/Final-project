package com.olga.couponsPhase3.entyties;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "Purchases")

public class Purchase implements Serializable{
	@Id
	@GeneratedValue
	@Column(name = "PURCHASE_ID", unique = true, nullable = false)
	private long purchaseId;
	
	@Column(name = "PURCHASE_DATE", nullable = false)
	private Date purchaseDate;
	
	@Column(name = "amount", nullable = false)
	private int amount;
	
	
	@ManyToOne
	private Coupon coupon;

	@ManyToOne
	@JsonIgnore
	private User user;
	
	
	
	//-----------------setters and getters-----------------
	
	public Date getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Coupon getCoupon() {
		return coupon;
	}
	public void Coupon(Coupon coupon) {
		this.coupon = coupon;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	//------------constructors--------------
	public Purchase(Date purchaseDate, long customerId, long couponId, int amount) {
		super();
		this.purchaseDate = purchaseDate;
		this.amount = amount;
	}

	public Purchase() {
		super();
	}


		//---------------to string-----------------
//		public String toString(){
//			return "customer id: "+user.getUserId()+"\ncoupon id: "+coupon.getCouponId()+"\namount: "+amount+"\npurchase date: "+
//					purchaseDate+"\n";
//		}
	

}
