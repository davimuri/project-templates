package davimuri.app.model;

import davimuri.app.enums.RoleEnum;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the user_role database table.
 * 
 */
@Entity
@Table(name="user_role")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRole u")
public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private RoleEnum role;
	private User user;

	public UserRole() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Enumerated(EnumType.STRING)
	public RoleEnum getRole() {
		return this.role;
	}

	public void setRole(RoleEnum role) {
		this.role = role;
	}


	//bi-directional many-to-one association to User
	@ManyToOne
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}