package davimuri.app.service;

import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import davimuri.app.enums.RoleEnum;
import davimuri.app.model.User;
import davimuri.app.repository.UserRepository;
import davimuri.app.util.HashUtil;

@Stateless
public class UserBean {

	@EJB
	private UserRepository userRepository;

	/**
	 * Creates a user with USER role
	 * @param user
	 */
	public void create(User user) {
		user.setActive(true);
		user.setInitialDate(Calendar.getInstance().getTime());
		user.setPassword(user.getPassword());
		user.addRole(RoleEnum.USER);

		userRepository.create(user);
	}

	/**
	 *
	 * @param username
	 * @param password
	 */
	public void updatePassword(String username, String password) {
		userRepository.updatePassword(username, password);
	}

	/**
	 * 
	 * @return
	 */
	public List<User> findByFilters(User user, RoleEnum roleEnum) {
		return userRepository.findByFilters(user, roleEnum);
	}
	
	/**
	 * Hashes the password
	 * @param plainStr
	 * @return
	 */
	private String hash(String plainStr) {
		return HashUtil.hashMD5(plainStr);
	}
}
