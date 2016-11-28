package davimuri.app.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Created by davidmurillomatallana on 27/11/16.
 */
public class HashUtilTest {

    @Test
    public void hashMD5Test() {
        String pass = "Hello345";

        String passHashed1 = null;
        String passHashed2 = null;
        passHashed1 = HashUtil.hashMD5(pass);
        passHashed2 = HashUtil.hashMD5(pass);

        assertEquals("Password hashed not equal", passHashed1, passHashed2);
    }
}
