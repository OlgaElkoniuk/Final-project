package com.olga.couponsPhase3.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.entyties.Company;
import com.olga.couponsPhase3.enums.ErrorType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.idao.ICompaniesDao;
import com.olga.couponsPhase3.utils.EmailValidation;

@Controller
public class CompanyController {
	@Autowired
	private ICompaniesDao companiesDao;

	private boolean isCreateCompanyValid(Company company, UserDataCashe userDataCashe) throws ApplicationException {

		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "Only administrator can add companies to DB");
		}
		if (isCompanyExistsByName(company.getName())) {
			throw new ApplicationException(ErrorType.INFO_ALREADY_EXISTS,
					"Company with name: " + company.getName() + " is already exist");
		}
		if (!EmailValidation.validateEmailAddress(company.getContactEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL,
					"email: " + company.getContactEmail() + " is not valid");
		}

		return true;
	}

	private boolean isCompanyExistsByName(String name) {
		if (companiesDao.findByName(name) == null) {
			return false;
		} else
			return true;
	}
	private boolean isCompanyExistsById(long id) {
		if (companiesDao.findByCompanyId(id) == null) {
			return false;
		} else
			return true;
	}
	

	private boolean isUpdateCompanyValid(Company company, UserDataCashe userDataCashe) throws ApplicationException {
		if (company.getCompanyId() == 0) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST, "there is no ID");
		}
		if (!isCompanyExistsById(company.getCompanyId())) {
			throw new ApplicationException(ErrorType.INFO_DOESNT_EXIST, "Company with such an id does not exist");
		}
		if (userDataCashe.getCompanyId() != company.getCompanyId()) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "You can update data only of your company");
		}
		if (!EmailValidation.validateEmailAddress(company.getContactEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL,
					"email: " + company.getContactEmail() + " is not valid");
		}

		return true;

	}

	public Long createCompany(Company company, UserDataCashe userDataCashe) throws ApplicationException {
		System.out.println(company);
		if (isCreateCompanyValid(company, userDataCashe)) {
			companiesDao.save(company);
			System.out.println("created");
			return company.getCompanyId();
		} else
			throw new ApplicationException(ErrorType.FAILED_CREATE, " failed create company");
	}

	public void updateCompany(Company company, UserDataCashe userDataCashe) throws ApplicationException {
		if (isUpdateCompanyValid(company, userDataCashe)) {
			companiesDao.save(company);
		} else
			throw new ApplicationException(ErrorType.FAILED_UPDATE, "failed update company");

	}

//	by deleting
//	company you
//	delete also
//	users which represent this company,
//	its coupons
//	and purchase
//	history of this
//	coupons.Mysql command
//	ON DELETE
//	CASCADE is responsible for this actions

	public void deleteCompany(long companyId, UserDataCashe userDataCashe) throws ApplicationException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP,
					"Only administrator can delete companies from DB");
		}
		if (isCompanyExistsById(companyId)) {
			companiesDao.deleteById(companyId);
		} else
			throw new ApplicationException(ErrorType.FAILED_DELETE,
					"failed delete company. company with id: " + companyId + " does not exist");

	}

	public List<Company> getAllCompanies(UserDataCashe userDataCashe) throws ApplicationException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP,
					"Only administrator can see all companies in DB");
		}
		return (List<Company>) companiesDao.findAll();
	}

	public Company getCompanyById(long companyId, UserDataCashe userDataCashe) throws ApplicationException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "Only administrator can get company by id");
		}
		if (isCompanyExistsById(companyId)) {
			return companiesDao.findByCompanyId(companyId);
		} else
			throw new ApplicationException(ErrorType.FAILED_READ,
					"failed get company. User with such an id does not exist");

	}

}
