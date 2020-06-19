package com.olga.couponsPhase3.logic;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Coupon;
import com.olga.couponsPhase3.entyties.Purchase;
import com.olga.couponsPhase3.entyties.User;
import com.olga.couponsPhase3.enums.ErrorType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.idao.ICouponsDao;
import com.olga.couponsPhase3.idao.IPurchaseDao;
import com.olga.couponsPhase3.idao.IUserDao;

@Controller
public class PurchaseController {
	@Autowired
	private IPurchaseDao purchasesDao;
	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private IUserDao userDao;
	
private boolean isCouponExistsById(long couponId) {
	if (couponsDao.findByCouponId(couponId)==null) {
		return false;
	}
	else return true;
}
	private boolean isAddPurchaseValid(long couponId,int amount, long customerId) throws ApplicationException, ParseException {
		if (!isCouponExistsById(couponId)) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST,"Coupon with id: " + couponId + " does not exist");
		}
		int amountOfCouponsAvailable = couponsDao.findByCouponId(couponId).getAmount();
		if (amountOfCouponsAvailable<amount) {
			throw new ApplicationException(ErrorType.VALIDATION_FAILED,"Amount of available coupons to purchase: " + amountOfCouponsAvailable);
		}

		return true;
	}


	public void addPurchase(Purchase purchase,UserDataCashe userDataCashe) throws ParseException, ApplicationException {
		long customerId = userDataCashe.getUserID();
		long couponId = purchase.getCoupon().getCouponId();
		purchase.setPurchaseDate(new Date());
		purchase.setUser(new User(customerId));
		int amount = purchase.getAmount();
		if (isAddPurchaseValid(couponId,amount,customerId)) {
			purchasesDao.save(purchase);
			Coupon coupon = couponsDao.findByCouponId(couponId);
			//Despite we already checked coupon availability i'm checking it again
			//in case that several purchases are happening in the same time
			if(coupon.getAmount()-amount<0) {
				throw new ApplicationException(ErrorType.GENERAL_ERROR, "Sorry, there is no enough coupons available");
			}
			coupon.setAmount(coupon.getAmount()-amount);
			couponsDao.save(coupon);
		} else
			throw new ApplicationException(ErrorType.FAILED_CREATE,"failed add purchase");
	}


	public List<Purchase> getPurchaseOfCustomerByCoupon(UserDataCashe userDataCashe, long couponId) throws ApplicationException, ParseException {
		User user = userDao.findByUserId(userDataCashe.getUserID());
		Coupon coupon = couponsDao.findByCouponId(couponId);
		if (!isCouponExistsById(couponId)) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST,"Coupon with id: " + couponId + " does not exist");
		}
		return	purchasesDao.findByCouponAndUser(coupon, user);
		 

	}
	public List<Purchase> getAllPurchasesByCouponId(long couponId,UserDataCashe userDataCashe) throws ApplicationException, ParseException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "Only administrator can get all purchases by coupon id from DB");
		}
		Coupon coupon = couponsDao.findByCouponId(couponId);
		if (coupon==null) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST,"Coupon with id: " + couponId + " does not exist");
		}
		return purchasesDao.findByCoupon(coupon);
	}
	public List<Purchase> getAllPurchasesByCustomerId(UserDataCashe userDataCashe) throws SQLException,  ApplicationException, ParseException {
		User user = userDao.findByUserId(userDataCashe.getUserID());
		return purchasesDao.findByUser(user);
	}
	public List<Purchase> getAllPurchases(UserDataCashe userDataCashe) throws ApplicationException, ParseException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "Only administrator can get all purchases from DB");
		}
		return (List<Purchase>) purchasesDao.findAll();
	}
	//there is no delete purchase methode because purchases are deleted automatically by "ON DELETE CASCADE" clause/
	//purchases will be deleted if company, coupon, user or customer is deleted

}
