package com.olga.couponsPhase3.idao;



import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.olga.couponsPhase3.entyties.Coupon;
import com.olga.couponsPhase3.entyties.Purchase;
import com.olga.couponsPhase3.entyties.User;



public interface IPurchaseDao extends CrudRepository<Purchase, Long>{

	public List<Purchase> findByCouponAndUser(Coupon coupon, User user);
	public List<Purchase> findByCoupon(Coupon coupon);
	public List<Purchase> findByUser( User user);
}
