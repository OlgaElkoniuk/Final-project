package com.olga.couponsPhase3.logic;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Coupon;
import com.olga.couponsPhase3.enums.ErrorType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.idao.ICompaniesDao;
import com.olga.couponsPhase3.idao.ICouponsDao;

@Controller
public class CouponController {

	@Autowired
	private ICouponsDao couponsDao;
	@Autowired
	private ICompaniesDao companiesDao;


	private boolean isStartDateValid(Date startDate) throws ApplicationException {
		if (startDate.before(new Date())) {
			throw new ApplicationException(ErrorType.INVALID_DATE, ". This date has passed, you enterd: " + startDate);
		} else
			return true;
	}

	private boolean isEndDateValid(Date endDate, Date startDate) throws ApplicationException {
		if (endDate.before(startDate)) {
			throw new ApplicationException(ErrorType.INVALID_DATE,
					". The end date cant be before start date, you enterd: " + endDate);
		} else
			return true;

	}
	
	private boolean isCouponExistsByTitle(String title) {
		if (couponsDao.findByTitle(title)==null) {
			return false;
		}
		else return true;
	}
	
	private boolean isCouponExistsById(long id) {
		if (couponsDao.findByCouponId(id)==null) {
			return false;
		}
		else return true;
	}

	private boolean isCreateCouponValid(Coupon coupon, UserDataCashe userDataCashe) throws ApplicationException {
//		if (!companiesDao.isCompanyExistsById(coupon.getCompanyId())) {
//			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST,
//					"Company with id: " + coupon.getCompanyId() + " does not exist");
//		}
//		if (userDataCashe.getCompanyId() != coupon.getCompanyId()) {
//			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "You can add coupons only to yours company");
//		}
		if (isCouponExistsByTitle(coupon.getTitle())) {
			throw new ApplicationException(ErrorType.INFO_ALREADY_EXISTS,
					"Coupon with title: " + coupon.getTitle() + " is already exist");
		}
		
		if (!isStartDateValid(coupon.getStartDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATE,
					" Coupon start date: " + coupon.getStartDate() + " is not valid");
		}
		if (!isEndDateValid(coupon.getEndDate(), coupon.getStartDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATE,
					"Coupon end date: " + coupon.getEndDate() + " is not valid");
		}
		if (coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.NEGATIVE_VALUE,
					"Price: " + coupon.getPrice() + " is not valid. Price should be bigger than 0");
		}
		if (coupon.getAmount() < 1) {
			throw new ApplicationException(ErrorType.NEGATIVE_VALUE,
					"Amount: " + coupon.getAmount() + " is not valid. Amount should be bigger than 1");
		}
		return true;
	}

	private boolean isUpdateCouponValid(Coupon coupon, UserDataCashe userDataCashe) throws ApplicationException {
		
		if (userDataCashe.getCompanyId() != coupon.getCompany().getCompanyId()) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "You can update coupons only to yours company");
		}
		if (coupon.getCouponId() == 0) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST, "there is no ID");
		}
		if (!isCouponExistsById(coupon.getCouponId())) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST, "Coupon with such an id does not exist");
		}

		if (!isStartDateValid(coupon.getStartDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATE,
					"Coupon start date: " + coupon.getStartDate() + " is not valid");
		}
		if (!isEndDateValid(coupon.getEndDate(), coupon.getStartDate())) {
			throw new ApplicationException(ErrorType.INVALID_DATE,
					"Coupon end date: " + coupon.getEndDate() + " is not valid");
		}
		if (coupon.getPrice() < 0) {
			throw new ApplicationException(ErrorType.NEGATIVE_VALUE,
					"Price: " + coupon.getPrice() + " is not valid. Price should be bigger than 0");
		}
		if (coupon.getAmount() < 1) {
			throw new ApplicationException(ErrorType.NEGATIVE_VALUE,
					"Amount: " + coupon.getAmount() + " is not valid. Amount should be bigger than 1");
		}
		return true;

	}

	public void createCoupon(Coupon coupon, UserDataCashe userDataCashe) throws ApplicationException {
		if (isCreateCouponValid(coupon, userDataCashe)) {
			coupon.setCompany(companiesDao.findByCompanyId(userDataCashe.getCompanyId()));
			couponsDao.save(coupon);
		} else
			throw new ApplicationException(ErrorType.FAILED_CREATE, "failed create coupon");
	}

	public void updateCoupon(Coupon coupon, UserDataCashe userDataCashe) throws ApplicationException {
		if (isUpdateCouponValid(coupon, userDataCashe)) {
			couponsDao.save(coupon);
		} else
			throw new ApplicationException(ErrorType.FAILED_UPDATE, "failed update coupon");

	}

	// by deleting coupon you delete also
	// its purchase history.
	// Mysql command ON DELETE CASCADE is responsible for this actions
	public void deleteCoupon(long couponId, UserDataCashe userDataCashe) throws ApplicationException, ParseException {
		if(!isCouponExistsById(couponId)) {
			throw new ApplicationException(ErrorType.FAILED_DELETE,
					"failed delete coupon. Coupon with id: " + couponId + " does not exist");
		}
		Coupon coupon = couponsDao.findByCouponId(couponId);
		if (userDataCashe.getCompanyId() != coupon.getCompany().getCompanyId()) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "You can delete coupons only of yours company");
		}
		couponsDao.deleteById(couponId);

	}

	public List<Coupon> getAllCoupons() throws ApplicationException, ParseException {
		return (List<Coupon>) couponsDao.findAll();
	}
	public List<Coupon> getAllCouponsByCompanyId(UserDataCashe userDataCashe) throws ApplicationException, ParseException {

		return couponsDao.findByCompany(companiesDao.findByCompanyId(userDataCashe.getCompanyId()));
	}
	public Coupon getCouponById(long couponId, UserDataCashe userDataCashe) throws ApplicationException, ParseException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "Only administrator can get coupon by ID");
		}
		if (isCouponExistsById(couponId)) {
			return couponsDao.findByCouponId(couponId);
		} else
			throw new ApplicationException(ErrorType.FAILED_READ,
					"failed get coupon. Coupon with an id: " + couponId + " does not exist");

	}
}
