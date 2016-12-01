package davimuri.app.view;

import davimuri.app.enums.RoleEnum;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by davidmurillomatallana on 30/11/16.
 */
@ManagedBean
@ApplicationScoped
public class ApplicationView {

    public RoleEnum[] getRoles() {
        return RoleEnum.values();
    }
}
