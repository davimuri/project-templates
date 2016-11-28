package davimuri.app.service;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import davimuri.app.enums.RoleEnum;
import davimuri.app.model.Role;
import davimuri.app.model.User;
import davimuri.app.model.UserRole;
import davimuri.app.repository.RoleRepository;
import davimuri.app.repository.UserRepository;
import davimuri.app.util.HashUtil;

@Stateless
public class UserBean {

	@EJB
	private UserRepository userRepository;

	@EJB
	private RoleRepository roleRepository;

	/**
	 * Creates a user with USER role
	 * @param user
	 */
	public void create(User user) {
		user.setActive(true);
		user.setInitialDate(Calendar.getInstance().getTime());
		user.setPassword(user.getPassword());

		Role role = roleRepository.findRole(RoleEnum.USER);
		user.addRole(role);

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
	 * Hashes the password
	 * @param plainStr
	 * @return
	 */
	private String hash(String plainStr) {
		return HashUtil.hashMD5(plainStr);
	}
}
