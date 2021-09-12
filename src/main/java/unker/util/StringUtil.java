package unker.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    /**
     * Return a {@link java.util.regex.Matcher} based on the data provided.
     * 
     * Returns null if the whole pattern is not matched.
     *  
     * @param commandPattern The regular expression pattern to match
     * @param data The data to handle.
     * @return A {@link java.util.regex.Matcher} with the results from the pattern.
     */
    public static Matcher parseUserInput(String commandPattern, String data) {
        if (data == null) {
            return null;
        }
        Pattern pattern = Pattern.compile(commandPattern);
        Matcher matcher = pattern.matcher(data);

        // Only return the Matcher if the input is valid
        if (matcher.matches()) {
            return matcher;
        } else {
            return null;
        }
    }
}
