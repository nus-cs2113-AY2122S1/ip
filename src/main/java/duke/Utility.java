package duke;

/**
 * General Class for utility functions
 */
public class Utility {
    private static final String INTEGER_REGEX = "\\d+$";

    /**
     * Returns whether String <code>message</code> is an integer.
     *
     * @param message String to be checked.
     * @return Boolean on whether the String is an Integer.
     */
    public static boolean isInteger(String message) {
        return message.matches(INTEGER_REGEX);
    }

}
