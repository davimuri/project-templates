package davimuri.app.util;

import davimuri.app.exception.ExceptionHandler;
import davimuri.app.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class to hash content
 * Created by davidmurillomatallana on 27/11/16.
 */
public class HashUtil {

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(HashUtil.class);

    /**
     * MD5 hash in hex format
     * @param value string to hash
     * @return
     */
    public static String hashMD5(String value) {

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            ExceptionHandler.handle(e, logger);
        }

        md.update(value.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();

        for(byte b1 : b){
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }

        return sb.toString();
    }
}
