package davimuri.app.view;

import davimuri.app.enums.MessagesEnum;
import davimuri.app.service.UserBean;
import davimuri.app.util.FacesUtil;
import davimuri.app.util.Messages;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Created by davidmurillomatallana on 27/11/16.
 */
@ManagedBean
@RequestScoped
public class ChangePasswordView {

    @EJB
    private UserBean userBean;

    private String password;

    public String save() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String username = externalContext.getUserPrincipal().getName();
        userBean.updatePassword(username, password);
        FacesUtil.addMessage(Messages.getInstance().getProperty(MessagesEnum.SUCCESSFUL_OPERATION));

        return null;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
