package com.olga.couponsPhase3.api;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Coupon;
import com.olga.couponsPhase3.enums.ClientType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.logic.CouponController;

@RestController
@RequestMapping("/coupons")
public class CouponApi {
	@Autowired
	private CouponController couponsController = null;

	@PostMapping
	public void createCoupon(@RequestParam("token") int token,@RequestBody Coupon coupon,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		couponsController.createCoupon(coupon, userDataCache);
	}
	@PutMapping
	public void updateCoupon(@RequestParam("token") int token,@RequestBody Coupon coupon,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		couponsController.updateCoupon(coupon, userDataCache);
	}
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@RequestParam("token") int token,@PathVariable("couponId") long id,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		couponsController.deleteCoupon(id, userDataCache);
	}

	@GetMapping 
	public List<Coupon> getAllCoupons(@RequestParam("token") int token,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		if (userDataCache.getClientType()==ClientType.COMPANY) {
			return couponsController.getAllCouponsByCompanyId(userDataCache);
		}
		else return couponsController.getAllCoupons();
		
	}
	@GetMapping ("/{couponId}")
	public void getCouponById(@RequestParam("token") int token,@PathVariable("couponId") long id,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		couponsController.getCouponById(id, userDataCache);
	}
}
