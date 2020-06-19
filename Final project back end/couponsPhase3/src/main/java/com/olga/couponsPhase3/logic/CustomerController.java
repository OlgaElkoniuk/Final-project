package com.olga.couponsPhase3.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Customer;
import com.olga.couponsPhase3.entyties.User;
import com.olga.couponsPhase3.enums.ErrorType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.idao.ICustomersDao;
import com.olga.couponsPhase3.idao.IUserDao;

@Controller
public class CustomerController {
	@Autowired
	private IUserDao usersDao;
	@Autowired
	private ICustomersDao customersDao;
	
//	private boolean isCustomerExistsById(long customerId) {
//		if(usersDao.findByUserId(customerId)==null) {
//			return false;
//		}
//		else return true;
//	}

	private boolean isCreateCustomerValid(Customer customer, UserDataCashe userDataCashe) throws ApplicationException {

		long customerId = userDataCashe.getUserID();
		User customerUser = usersDao.findByUserId(customerId);
		if (customerUser.getCompany().getCompanyId() == null) {
			return true;
		} else
			throw new ApplicationException(ErrorType.VALIDATION_FAILED,
					" User with an email: " + customer.getUser().getEmail() + " is registered"
							+ "as a company user. If you want to register as customer please create another account.");

	}

//	private boolean isUpdateCustomerValid(Customer customer, UserDataCashe userDataCashe) throws ApplicationException {
//		if (customer.getId() == 0) {
//			throw new ApplicationException(ErrorType.VALIDATION_FAILED, " User has no id: " + customer.getId());
//		}
//		if (usersDao.isUserExsistById(customer.getUser().getId())) {
//			throw new ApplicationException(ErrorType.VALIDATION_FAILED, " User with such an id does not exist");
//		}
//		return true;
//	}

	public void createCustomer(Customer customer, UserDataCashe userDataCashe) throws ApplicationException {
		if (isCreateCustomerValid(customer, userDataCashe)) {
			customersDao.save(customer);
		} else
			throw new ApplicationException(ErrorType.FAILED_CREATE, " failed create customer");
	}

	public void updateCustomer(Customer customer, UserDataCashe userDataCashe) throws ApplicationException {
		customer.setId(userDataCashe.getUserID());

		customersDao.save(customer);

	}

	// by deleting customer you delete also
	// its purchase history.
	// Mysql command ON DELETE CASCADE is responsible for this actions
	public void deleteCustomer(UserDataCashe userDataCashe) throws ApplicationException {

		usersDao.deleteById(userDataCashe.getUserID());
		// customer and its purchases will be deleted automatically because of ON DELETE
		// CASCADE clause MySQL
	}

	public List<Customer> getAllCustomers(UserDataCashe userDataCashe) throws ApplicationException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP,
					"Only administrator can get all customers from DB");
		}
		return (List<Customer>) customersDao.findAll();
	}

	public Customer getCustomerById(UserDataCashe userDataCashe) throws ApplicationException {
		return customersDao.findByCustomerId(userDataCashe.getUserID());
	}
}
