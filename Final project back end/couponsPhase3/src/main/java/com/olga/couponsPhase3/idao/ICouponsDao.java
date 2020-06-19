package com.olga.couponsPhase3.idao;


import java.util.List;
import java.util.Locale.Category;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.olga.couponsPhase3.entyties.Company;
import com.olga.couponsPhase3.entyties.Coupon;


public interface ICouponsDao extends CrudRepository<Coupon, Long>{

	public Coupon findByTitle(String couponTitle);
	
	public Coupon findByCouponId(long id);

	public List<Coupon> findByCompany(Company company);

	public List<Coupon> findByCompanyAndCategory(Company company, Category category);

	@Query(value = "SELECT * FROM coupons WHERE coupon_price <= :couponMaxPrice", nativeQuery = true)
	public List<Coupon> getAllCouponsByPrice(@Param("couponMaxPrice") Double couponMaxPrice);

	@Query(value = "SELECT * FROM coupons WHERE coupon_category = :category", nativeQuery = true)
	public List<Coupon> getAllCouponsByCategory(@Param("category") Category category);

	@Query(value = "SELECT * FROM coupons WHERE company_company_id = :companyId", nativeQuery = true)
	public List<Coupon> getAllCouponsByCompany(@Param("companyId") long companyId);

	@Query(value = "SELECT * FROM coupons WHERE coupon_id IN (SELECT coupon_coupon_id FROM purchases WHERE customer_user_user_id = :customerId)", nativeQuery = true)
	public List<Coupon> getAllCouponsByCustomer(@Param("customerId") long customerId);
	


}
