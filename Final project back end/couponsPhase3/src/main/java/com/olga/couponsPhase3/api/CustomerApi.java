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
import com.olga.couponsPhase3.entyties.Customer;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.logic.CustomerController;


@RestController
@RequestMapping("/customer")
public class CustomerApi {
	@Autowired
	private CustomerController customerController = null;

	@PostMapping
	public void createCustomer(@RequestParam("token") int token,@RequestBody Customer customer,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		customerController.createCustomer(customer, userDataCache);
	}
	@PutMapping
	public void updateCustomer(@RequestParam("token") int token,@RequestBody Customer customer,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		customerController.updateCustomer(customer, userDataCache);
	}
	@DeleteMapping("/{customerId}")
	public void deleteCustomer(@RequestParam("token") int token,@PathVariable("customerId") long id,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		customerController.deleteCustomer(userDataCache);
	}

	@GetMapping 
	public List<Customer> getAllCustomers(@RequestParam("token") int token,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		return customerController.getAllCustomers(userDataCache);
	}
	@GetMapping ("/{customerId}")
	public Customer getCustomerById(@RequestParam("token") int token,@PathVariable("customerId") long id,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		return customerController.getCustomerById(userDataCache);
	}
}
