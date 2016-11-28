package davimuri.app.util;

import static org.junit.Assert.*;

import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Created by davidmurillomatallana on 27/11/16.
 */
public class HashUtilTest {

    @Test
    public void hashMD5Test() {
        String pass = "Hello345";

        String passHashed1 = null;
        String passHashed2 = null;

        try {
            passHashed1 = HashUtil.hashMD5(pass);
            passHashed2 = HashUtil.hashMD5(pass);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }

        assertEquals("Password hashed not equal", passHashed1, passHashed2);
    }
}
