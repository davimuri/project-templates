package davimuri.app.exception;

/**
 * Created by davidmurillomatallana on 27/11/16.
 */
public class TechnicalException extends RuntimeException {

    public TechnicalException() {
        super();
    }

    public TechnicalException(String msg) {
        super(msg);
    }

    public TechnicalException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
