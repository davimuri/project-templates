package davimuri.app.repository;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import davimuri.app.model.Role;
import davimuri.app.model.User;

/**
 * Repository with persistence operations to user accounts
 * @author davidmurillomatallana
 *
 */
@Stateless
public class UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Creates an user
	 * @param user
	 */
	public void create(User user) {
		entityManager.persist(user);
	}

	/**
	 * Find roles that matches with the names received
	 * @param roleNames List of role names
	 * @return
	 */
	public List<Role> findRoles(List<String> roleNames) {
		return entityManager.createNamedQuery("Role.findByNames", Role.class)
			.setParameter("names", roleNames)
			.getResultList();
	}
}
