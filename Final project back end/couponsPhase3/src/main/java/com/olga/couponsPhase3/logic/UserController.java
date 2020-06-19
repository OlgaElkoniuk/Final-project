package com.olga.couponsPhase3.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.olga.couponsPhase3.beans.LogIn;
import com.olga.couponsPhase3.beans.UserDataCashe;
import com.olga.couponsPhase3.beans.UserDataClient;
import com.olga.couponsPhase3.cache.ICacheManager;
import com.olga.couponsPhase3.entyties.User;
import com.olga.couponsPhase3.enums.ClientType;
import com.olga.couponsPhase3.enums.ErrorType;
import com.olga.couponsPhase3.exceptions.ApplicationException;
import com.olga.couponsPhase3.idao.ICompaniesDao;
import com.olga.couponsPhase3.idao.IUserDao;
import com.olga.couponsPhase3.utils.EmailValidation;

@Controller
public class UserController {
	@Autowired
	private IUserDao usersDao;
	@Autowired
	private ICompaniesDao companiesDao;
	@Autowired
	private ICacheManager cacheManager;

	private boolean isPasswordValid(String password) {
		if (password.length() >= 8 && password.length() <= 14) {
			return true;
		} else
			return false;

	}

	private boolean isUserExsistByUserName(String userName) {
		if (usersDao.findByEmail(userName) == null) {
			return false;
		} else
			return true;
	}

	private boolean isUserExsistById(long id) {
		if (usersDao.findById(id) == null) {
			return false;
		} else
			return true;
	}

	private UserDataCashe getUserDataByUserName(String userName) {
		User user = usersDao.findByEmail(userName);
		UserDataCashe userData = new UserDataCashe();
		if(user.getClientType()==ClientType.COMPANY) {
		userData = new UserDataCashe(user.getUserId(), user.getClientType(), user.getCompany().getCompanyId());
				return userData;		
		}
		else 
		userData = new UserDataCashe(user.getUserId(), user.getClientType());
		return userData;
	}

	private boolean isCreateUserValid(User user) throws ApplicationException {
		if (isUserExsistByUserName(user.getEmail())) {
			throw new ApplicationException(ErrorType.INFO_ALREADY_EXISTS,
					"User with email: " + user.getEmail() + " is already exist");
		}
		if (!EmailValidation.validateEmailAddress(user.getEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL, "email: " + user.getEmail() + " is not valid");
		}
		if (!isPasswordValid(user.getPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD,
					"password should not contain less than 8 ccharacters and more than 14. Your password is "
							+ user.getPassword().length() + " long");
		}
		if (user.getClientType()==ClientType.COMPANY) {
			if (companiesDao.findByCompanyId(user.getCompany().getCompanyId())!=null) {
				return true;
			}
			else throw new ApplicationException(ErrorType.LOGIN_FAILED, "There is no company with ID"+user.getCompany().getCompanyId());
		}

		return true;
	}

	private boolean isUpdateUserValid(User user, UserDataCashe userDataCashe) throws ApplicationException {

		if (!EmailValidation.validateEmailAddress(user.getEmail())) {
			throw new ApplicationException(ErrorType.INVALID_EMAIL, "email: " + user.getEmail() + " is not valid");
		}
		if (!isPasswordValid(user.getPassword())) {
			throw new ApplicationException(ErrorType.INVALID_PASSWORD,
					"password should not contain more than 8 ccharacters. Your password is "
							+ user.getPassword().length() + " long");
		}
		if (user.getCompany().getCompanyId() == null) {
			return true;
		} else if (companiesDao.findByCompanyId(user.getCompany().getCompanyId()) != null) {
			return true;
		}

		return false;

	}

	public long createUser(User user) throws ApplicationException {
		if (isCreateUserValid(user)) {
			usersDao.save(user);
			return usersDao.findByEmail(user.getEmail()).getUserId();
		} else
			throw new ApplicationException(ErrorType.FAILED_CREATE, "failed create user");
	}

	public void updateUser(User user, UserDataCashe userDataCashe) throws ApplicationException {
		if (isUpdateUserValid(user, userDataCashe)) {
			user.setId(userDataCashe.getUserID());
			usersDao.save(user);
		} else
			throw new ApplicationException(ErrorType.FAILED_UPDATE, "failed update user");

	}

	// by deleting user you delete also customer which references to this user,
	// and its purchase history.
	// Mysql command ON DELETE CASCADE is responsible for this actions
	public void deleteUser(long userId, UserDataCashe userDataCashe) throws ApplicationException {
		if (!isUserExsistById(userId)) {
			throw new ApplicationException(ErrorType.FAILED_DELETE,
					"failed delete user. User with such an id does not exist");
		}
		if (userDataCashe.getUserID() != usersDao.findByUserId(userId).getUserId()) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "You can update only yours account");
		}

		usersDao.deleteById(userId);

	}

	public List<User> getAllUsers(UserDataCashe userDataCashe) throws ApplicationException {
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP,
					"Only administrator can get all users from the DB");
		}
		return (List<User>) usersDao.findAll();

	}

	public User getUserById(long userId, UserDataCashe userDataCashe) throws ApplicationException {
		if (!isUserExsistById(userId)) {
			throw new ApplicationException(ErrorType.FAILED_READ,
					" failed get user. User with such an id does not exist");
		}
		if (!userDataCashe.getClientType().name().equals("ADMIN")) {
			throw new ApplicationException(ErrorType.UNAUTHORISED_HTTP, "Only administrator can get a usesr by id");
		}
		return usersDao.findByUserId(userId);
	}

	public UserDataClient login(LogIn login) throws ApplicationException {
		int token = generateEncryptedToken(login.getUserName());
		UserDataCashe userData = getUserDataByUserName(login.getUserName());

		cacheManager.put(token, userData);

		ClientType clientType = logIn(login.getUserName(), login.getPassword());
		UserDataClient userDataClient = new UserDataClient(token, clientType, userData.getUserID(), login.getUserName(),
				userData.getCompanyId());
		return userDataClient;
	}

	private ClientType logIn(String userName, String password) throws ApplicationException {
		User user = usersDao.findByEmailAndPassword(userName, password);

		if (user == null) {
			throw new ApplicationException(ErrorType.LOGIN_FAILED,
					"Your password doesn't fit user name: " + userName + " or this user name does not exist");
		}
//			ClientType clientType = ClientType.valueOf(clientTypeString);
		ClientType clientType = user.getClientType();
		return clientType;
	}

	private int generateEncryptedToken(String userName) {
		String token = userName + (Math.random() * 100000) + 1;
		return token.hashCode();

	}

}
