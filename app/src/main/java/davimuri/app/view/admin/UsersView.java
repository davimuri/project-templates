package davimuri.app.view.admin;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

import davimuri.app.model.User;
import davimuri.app.service.UserBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@ViewScoped
public class UsersView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(UsersView.class);

	@EJB
	private UserBean userBean;
	
	private List<User> users;

	private User user;

	public UsersView() {
		user = new User();
	}

	public String search() {
		logger.debug("search()");
		logger.debug("loading users...");
		loadUsers();
		logger.debug("users loaded {}", users.size());

		return null;
	}

	public void loadUsers() {
		users = userBean.find();
	}


	public List<User> getUsers() {
		logger.debug("getUsers()");
		logger.debug("loading users...");
		loadUsers();
		logger.debug("users loaded {}", users.size());
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}
	
    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("User Edited", ((User) event.getObject()).getUsername());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(((User) event.getObject()).getUsername()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
