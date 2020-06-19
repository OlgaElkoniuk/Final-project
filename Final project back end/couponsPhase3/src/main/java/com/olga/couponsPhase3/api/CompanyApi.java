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
import com.olga.couponsPhase3.entyties.Company;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.logic.CompanyController;


@RestController
@RequestMapping("/company")
public class CompanyApi {
	@Autowired
	private CompanyController companyController = null;

	@PostMapping
	public void createCompany(@RequestParam("token") int token,@RequestBody Company company, HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		companyController.createCompany(company,userDataCache);
	}
	@PutMapping
	public void updateCompany(@RequestParam("token") int token,@RequestBody Company company, HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		companyController.updateCompany(company, userDataCache);
	}
	@DeleteMapping("/{companyId}")
	public void deleteCompany(@RequestParam("token") int token,@PathVariable("companyId") long id,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		companyController.deleteCompany(id, userDataCache);
	}

	@GetMapping 
	public List<Company> getAllCompanies(@RequestParam("token") int token,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		return companyController.getAllCompanies(userDataCache);
	}
	@GetMapping ("/{companyId}")
	public Company getCompanyById(@RequestParam("token") int token,@PathVariable("companyId") long id,HttpServletRequest request) throws ApplicationException, ParseException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		return companyController.getCompanyById(id,userDataCache);
	}
}
