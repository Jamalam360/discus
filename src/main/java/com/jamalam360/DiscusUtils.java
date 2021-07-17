package com.jamalam360;

import java.util.Locale;

/**
 * @author Jamalam360
 */
public class DiscusUtils {
    public static String cleanFileName(String original) {
        return original.split("\\.")[0].toLowerCase(Locale.ROOT);
    }
}
