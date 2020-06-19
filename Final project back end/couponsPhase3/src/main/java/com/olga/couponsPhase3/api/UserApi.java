package com.olga.couponsPhase3.api;

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

import com.olga.couponsPhase3.beans.LogIn;
import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.beans.UserDataClient;
import com.olga.couponsPhase3.entyties.User;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.logic.UserController;

@RestController
@RequestMapping("/user")
public class UserApi {
	@Autowired
	private UserController userController = null;

	
	@PostMapping()
	public void register(@RequestBody User user) throws ApplicationException {
		userController.createUser(user);
		
	}
	@PostMapping("/login")
	public UserDataClient login(@RequestBody LogIn login) throws ApplicationException {
		return userController.login(login);
	}
	@PutMapping
	public void updateUser(@RequestParam("token") int token,@RequestBody User user,HttpServletRequest request) throws ApplicationException{
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		userController.updateUser(user, userDataCache);
	}
	@DeleteMapping("/{userId}")
	public void deleteUser(@RequestParam("token") int token,@PathVariable("userId") long id,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		userController.deleteUser(id, userDataCache);
	}

	@GetMapping ("/{userId}")
	public User getUser(@RequestParam("token") int token,@PathVariable("userId") long id,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");	
			return userController.getUserById(id, userDataCache);
	}

	@GetMapping 
	public List<User> getAllUser(@RequestParam("token") int token,HttpServletRequest request) throws ApplicationException {
		UserDataCashe userDataCache = (UserDataCashe) request.getAttribute("userData");
		return userController.getAllUsers(userDataCache);
	}
}
