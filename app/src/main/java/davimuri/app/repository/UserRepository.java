package davimuri.app.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(User user) {
		entityManager.persist(user);
	}

	/**
	 * Updates password of a user
	 * @param username
	 * @param password
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updatePassword(String username, String password) {
		List<User> users = entityManager.createNamedQuery("User.findByUsername", User.class)
				.setParameter("username", username)
				.getResultList();

		if (!users.isEmpty()) {
			User user = users.get(0);
			user.setPassword(password);
		}
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
/*
	public List<User> find(User filter) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT u FROM User u ");

		Map<String, Object> params = new HashMap<>();

		if (filter.getActive() != null) {
			sb.append(" u.active = :active ");
			params.put("active", filter.getActive());
		}
		if (filter.getInitialDate() != null) {
			sb.append(" u.initialDate >= :initialDate ");
			params.put("initialDate", filter.getInitialDate());
		}
		if (filter.getFinalDate() != null) {
			sb.append(" u.finalDate <= :finalDate ");
			params.put("finalDate", filter.getFinalDate());
		}

		entityManager.createQuery("SELECT u FROM User u ")
	}
*/
	/**
	 * Returns all users in database
	 * @return
	 */
	public List<User> findAll() {
		return entityManager.createNamedQuery("User.findAll", User.class)
				.getResultList();
	}
}
