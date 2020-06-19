package com.olga.couponsPhase3.idao;


import org.springframework.data.repository.CrudRepository;

import com.olga.couponsPhase3.entyties.User;


public interface IUserDao extends CrudRepository<User, Long>{

	public User findByEmail(String userName);
	public User findByUserId (long id);
	public User findByEmailAndPassword(String userName, String userPassword);

}
