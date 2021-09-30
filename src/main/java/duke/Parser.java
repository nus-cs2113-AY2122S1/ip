package duke;

/**
 * General Class for handling parsing of Strings and String Arrays.
 */
public class Parser {
    public static final String WHITESPACE_REGEX = "\\s+";
    private static final String PIPE_REGEX = "\\|";
    private static final String FIRST_ARGUMENT_REGEX = "^%s\\s*";

    /**
     * Takes in a String and returns a String Array split between all whitespace.
     *
     * @param message String to be split.
     * @return String Array of split message.
     */
    public static String[] splitWhitespace(String message) {
        return message.split(WHITESPACE_REGEX);
    }

    /**
     * Takes in a String and returns a String Array split between all '|' characters.
     *
     * @param message String to be split.
     * @return String Array of split message.
     */
    public static String[] splitPipe(String message) {
        return message.split(PIPE_REGEX);
    }

    /**
     * Takes in a String and returns a String of the first String after having split them by whitespace.
     *
     * @param message String to have first String extracted from.
     * @return first String before whitespace.
     */
    public static String getFirstArgument(String message) {
        return message.split(WHITESPACE_REGEX)[0];
    }

    /**
     * Takes in a String and returns a String of everything after the first string and trailing whitespace.
     *
     * @param message String to have first String extracted out.
     * @return Everything after the first string and trailing whitespace
     */
    public static String removeFirstArgument(String message) {
        String[] userInputSplit = splitWhitespace(message);
        return message.replaceAll(String.format(FIRST_ARGUMENT_REGEX, userInputSplit), "");
    }

    /**
     * Takes in a user input and split String. Splits the user input based on the <code>split</code> String
     * along with any whitespace before and after the split. returns the split Array.
     *
     * @param userInput String to be split.
     * @param split     What to use to split user input String.
     * @return Array of Strings that have been split bu the <code>split</code> string including whitespace.
     */
    public static String[] splitOnArgument(String userInput, String split) {
        return userInput.split(WHITESPACE_REGEX + split + WHITESPACE_REGEX);
    }

}
