package davimuri.app.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Helper methods for JSF
 * @author davidmurillomatallana
 *
 */
public class FacesUtil {

	/**
	 * Adds a message to the faces context in order to show
	 * the message in a web page
	 * @param message
	 */
	public static void addMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(message));
	}

	/**
	 * Adds an error message to the faces context in order to show
	 * the message in a web page
	 * @param message
	 */
	public static void addErrorMessage(String message) {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", message));
	}
}
