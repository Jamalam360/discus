package io.github.jamalam360;

import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;

import java.util.Locale;

/**
 * @author Jamalam360
 */
public class DiscusUtils {
    public static String cleanFileName(String original) {
        String modified = original.split( "\\.")[0].toLowerCase(Locale.ROOT);

        if (Identifier.isValid(Identifiers.id(modified).toString())) {
            return modified;
        } else {
            throw new InvalidIdentifierException("Discus failed to format one of your file names, please refer to the wiki to find out how files should be named");
        }
    }
}
