package davimuri.app.util;

import davimuri.app.enums.MessagesEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * Created by davidmurillomatallana on 27/11/16.
 */
public class Messages {

    private static final String PROPERTIES_FILE = "davimuri.app.messages";

    private static final Logger logger = LoggerFactory.getLogger(Messages.class);

    private static Messages instance;

    private ResourceBundle resourceBundle;

    private MessageFormat messageFormat;

    private Messages() {
        resourceBundle = ResourceBundle.getBundle(PROPERTIES_FILE);
        messageFormat = new MessageFormat("");
    }

    public static Messages getInstance() {
        if (instance == null) {
            instance = new Messages();
        }

        return instance;
    }

    public String getProperty(MessagesEnum messagesEnum, Object... params) {
        String msg = resourceBundle.getString(messagesEnum.key());

        if (params != null) {
            messageFormat.applyPattern(msg);
            msg = messageFormat.format(params);
        }

        return msg;
    }
}
