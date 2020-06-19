package com.olga.couponsPhase3.entyties;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.olga.couponsPhase3.enums.Categories;

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

@Entity
@Table(name = "coupons")
public class Coupon implements Serializable{
	
	
	@Id
	@GeneratedValue
	@Column(name = "COUPON_ID")
	private long couponId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "CATEGORY", nullable = false)
	private Categories category;
	
	@Column(name = "TITLE", nullable = false)
	private String title;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;
	
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;
	
	@Column(name = "AMOUNT", nullable = false)
	private int amount;
	
	@Column(name = "PRICE", nullable = false)
	private double price;
	
	@Column(name = "IMAGE")
	private String image;
	
	
	@ManyToOne
	private Company company;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "coupon", cascade = CascadeType.REMOVE)
	@JsonIgnore
	private List<Purchase> purchases;
	//------------------------setters and getters----------------------
	
	public long getCouponId() {
		return couponId;
	}
	public void setCouponId(long id) {
		this.couponId = id;
	}
	public Categories getCategory() {
		return category;
	}
	public int getCategoryId() {
		return getCategory().ordinal()+1;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
	
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
	this.endDate = endDate;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	//-------------------constructor------------------
	/**
	 * constructor to collect info
	 * @param company_id
	 * @param coupon_id
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param price
	 * @param image
	 * @throws InvalidDateException
	 * @throws InvalidNumberException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Coupon(long company_id, long coupon_id, Categories category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image) {
		super();
		
		setCouponId(coupon_id);
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
	/**
	 * constructor to add info
	 * @param company_id
	 * @param category
	 * @param title
	 * @param description
	 * @param startDate
	 * @param endDate
	 * @param amount
	 * @param price
	 * @param image
	 * @throws InvalidDateException
	 * @throws InvalidNumberException
	 * @throws InvalidAmountOfCharactersException
	 */
	public Coupon(long company_id, Categories category, String title,
			String description, Date startDate, Date endDate, int amount,
			double price, String image)  {
		super();
		
		setCategory(category);
		setTitle(title);
		setDescription(description);
		setStartDate(startDate);
		setEndDate(endDate);
		setAmount(amount);
		setPrice(price);
		setImage(image);
	}
	
public Coupon() {
		super();
	}
//---------------to string--------------------------
	public String toString(){
		return "coupon id: "+couponId+"\ncoupon category: "+category+"\ntitle: "+title
				+"\ndescription: "+description+"\nstart date: "+startDate+"\nend date: "+endDate
				+"\namount: "+amount+"\nprice: "+price+"\nimage: "+image;
	}

}
