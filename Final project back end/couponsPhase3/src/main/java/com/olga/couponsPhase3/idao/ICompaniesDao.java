package com.olga.couponsPhase3.idao;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.olga.couponsPhase3.entyties.Company;



@Repository
public interface ICompaniesDao extends CrudRepository<Company, Long>{

	public Company findByContactEmail(String contactEmail);

	public Company findByName(String companyName);
	
	public Company findByCompanyId(long id);
	
}
