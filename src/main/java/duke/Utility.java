package duke;

/**
 * General Class for utility functions
 */
public class Utility {
    private static final String INTEGER_REGEX = "\\d+$";

    /**
     * Checks that String is an Integer
     *
     * @param message String to be checked.
     * @return Boolean on whether the String is an Integer.
     */
    public static boolean isInteger(String message) {
        return message.matches(INTEGER_REGEX);
    }
    
}
