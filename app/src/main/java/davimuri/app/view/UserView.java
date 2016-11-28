package davimuri.app.view;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import davimuri.app.model.User;
import davimuri.app.service.UserBean;

@ManagedBean
@RequestScoped
public class UserView {

	@EJB
	private UserBean userBean;
	
	/**
	 * The user account data provided by the user
	 */
	private User user;

	public UserView() {
		user = new User();
	}
	
	/**
	 * Gets the data from the user to create
	 * an user account
	 * @return
	 */
	public String save() {
		userBean.create(user);
		
		return null;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
