package davimuri.app.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
	@NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username")
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Boolean active;
	private Date finalDate;
	private Date initialDate;
	private String password;
	private String username;
	private List<UserRole> userRoles;

	public User() {
		userRoles = new ArrayList<>();
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="final_date")
	public Date getFinalDate() {
		return this.finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="initial_date")
	public Date getInitialDate() {
		return this.initialDate;
	}

	public void setInitialDate(Date initialDate) {
		this.initialDate = initialDate;
	}

	@NotNull
	@Size(max=50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotNull
	@Size(min=8, max=50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="user")
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addRole(Role role) {
		UserRole userRole = new UserRole();
		userRole.setRole(role);

		return addUserRole(userRole);
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setUser(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setUser(null);

		return userRole;
	}

}