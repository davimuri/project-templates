package davimuri.app.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import davimuri.app.enums.RoleEnum;
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
	 * Finds users according to receiving filters
	 * @param filter
	 * @param roleEnum
	 * @return
	 */
	public List<User> findByFilters(User filter, RoleEnum roleEnum) {
		StringBuilder sb = new StringBuilder();

		Map<String, Object> params = new HashMap<>();

		if (filter.getActive() != null) {
			sb.append(" u.active = :active AND ");
			params.put("active", filter.getActive());
		}
		if (filter.getInitialDate() != null) {
			sb.append(" u.initialDate >= :initialDate AND ");
			params.put("initialDate", filter.getInitialDate());
		}
		if (filter.getFinalDate() != null) {
			sb.append(" u.finalDate <= :finalDate AND ");
			params.put("finalDate", filter.getFinalDate());
		}
		if (roleEnum != null) {
			sb.append(" ur.role = :role AND ");
			params.put("role", roleEnum);
		}

		String query = "SELECT u FROM User u JOIN u.userRoles ur ";

		if (sb.length() > 0) {
			query += " WHERE " + sb.substring(0, sb.length() - 5)
				+ " ORDER BY u.username ASC";
		}

		TypedQuery<User> typedQuery = entityManager.createQuery(query, User.class);

		for (Map.Entry<String, Object> entry : params.entrySet()) {
			typedQuery.setParameter(entry.getKey(), entry.getValue());
		}

		return typedQuery.getResultList();
	}

	/**
	 * Returns all users in database
	 * @return
	 */
	public List<User> findAll() {
		return entityManager.createNamedQuery("User.findAll", User.class)
				.getResultList();
	}
}
