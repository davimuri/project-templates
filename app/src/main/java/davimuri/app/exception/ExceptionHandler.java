package davimuri.app.exception;

import davimuri.app.enums.MessagesEnum;
import davimuri.app.util.Messages;
import org.slf4j.Logger;

/**
 *
 * Created by davidmurillomatallana on 27/11/16.
 */
public class ExceptionHandler {

    /**
     * Logs the exception received and throws a technical exception
     * to show generic message to users
     * @param e
     * @param logger
     * @throws TechnicalException
     */
    public static void handle(Exception e, Logger logger) throws TechnicalException {
        logger.error(e.toString(), e);
        String msg = Messages.getInstance().getProperty(MessagesEnum.INTERNAL_ERROR);
        throw new TechnicalException(msg);
    }
}
