package davimuri.app.model.view;

import javax.faces.bean.ManagedBean;

import davimuri.app.model.User;

@ManagedBean
public class UserView {

	private User user;

	public UserView() {
		
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
