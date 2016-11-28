package davimuri.app.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r"),
	@NamedQuery(name="Role.findByName", query="SELECT r FROM Role r WHERE r.name IN :name"),
	@NamedQuery(name="Role.findByNames", query="SELECT r FROM Role r WHERE r.name IN (:names)")
})
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private List<UserRole> userRoles;

	public Role() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to UserRole
	@OneToMany(mappedBy="role")
	public List<UserRole> getUserRoles() {
		return this.userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public UserRole addUserRole(UserRole userRole) {
		getUserRoles().add(userRole);
		userRole.setRole(this);

		return userRole;
	}

	public UserRole removeUserRole(UserRole userRole) {
		getUserRoles().remove(userRole);
		userRole.setRole(null);

		return userRole;
	}

}