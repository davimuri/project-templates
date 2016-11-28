package davimuri.app.enums;

/**
 * Defines messages to use in exception or error handling
 * and for business validations.
 *
 * This class is intended to be used with
 * @see davimuri.app.util.Messages
 *
 * Created by davidmurillomatallana on 27/11/16.
 */
public enum MessagesEnum {

    INTERNAL_ERROR("error.internal"),
    SUCCESSFUL_OPERATION("successful.operation");

    /**
     * Key name of the property that identify the message in
     * properties file
     */
    private String key;

    private MessagesEnum(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
