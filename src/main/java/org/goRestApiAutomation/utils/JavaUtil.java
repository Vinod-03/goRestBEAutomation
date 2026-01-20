package org.goRestApiAutomation.utils;

import java.util.UUID;
public class JavaUtil {

    public static String getRandomEmail() {
        return "user_"
                + UUID.randomUUID().toString().replace("-", "")
                + "@testmail.com";
    }

    /**
     * Optional: random string generator
     */
    public static String getRandomString(int length) {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, length);
    }

}
