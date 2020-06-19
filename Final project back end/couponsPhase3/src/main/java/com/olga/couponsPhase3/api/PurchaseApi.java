package com.olga.couponsPhase3.api;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Purchase;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.logic.PurchaseController;

@RestController
@RequestMapping("/purchase")
public class PurchaseApi {
	@Autowired
	private PurchaseController purchaseController = null;

	@PostMapping
	public void createPurchase(@RequestParam("token") int token,@RequestBody Purchase purchase, HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		purchaseController.addPurchase(purchase, userDataCache );
	}

	@GetMapping 
	public void getAllPurchase(@RequestParam("token") int token, HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		purchaseController.getAllPurchases(userDataCache);
	}
	@GetMapping ("/{couponId}")
	public void getPurchaseOfCustomerByCoupon(@RequestParam("token") int token,@PathVariable("couponId") long id, HttpServletRequest request) 
			throws ApplicationException, ParseException {
		UserDataCashe userData = (UserDataCashe) request.getAttribute("userData");
		purchaseController.getPurchaseOfCustomerByCoupon(userData, id);
	}
}
