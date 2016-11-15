package davimuri.app.service;

import java.util.Calendar;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import davimuri.app.model.User;
import davimuri.app.repository.UserRepository;

@Stateless
public class UserBean {

	@EJB
	private UserRepository userRepository;
	
	public void create(User user) {
		user.setActive(true);
		user.setInitialDate(Calendar.getInstance().getTime());
		user.setPassword(hash(user.getPassword()));
		userRepository.create(user);
	}
	
	private String hash(String plainStr) {
		return plainStr;
	}
}
