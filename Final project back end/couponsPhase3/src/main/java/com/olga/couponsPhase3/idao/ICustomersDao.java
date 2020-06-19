package com.olga.couponsPhase3.idao;

import org.springframework.data.repository.CrudRepository;

import com.olga.couponsPhase3.entyties.Customer;


public interface ICustomersDao extends CrudRepository<Customer, Long>{
	public Customer findByCustomerId (long id);

}
