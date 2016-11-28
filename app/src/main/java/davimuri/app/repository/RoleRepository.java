package davimuri.app.repository;

import davimuri.app.enums.RoleEnum;
import davimuri.app.model.Role;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by davidmurillomatallana on 27/11/16.
 */
@Stateless
public class RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Role findRole(RoleEnum roleEnum) {
        List<Role> roles = entityManager.createNamedQuery("Role.findByName", Role.class)
                .setParameter("name", roleEnum.name())
                .getResultList();

        if (!roles.isEmpty()) {
            return roles.get(0);
        }

        return null;
    }

}
