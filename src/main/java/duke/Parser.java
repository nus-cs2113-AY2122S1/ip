package duke;

public class Parser {
    public static final String WHITESPACE_REGEX = "\\s+";
    public static final String PIPE_REGEX = "\\|";

    public static String[] splitWhitespace(String message) {
        return message.split(WHITESPACE_REGEX);
    }

    public static String[] splitPipe(String message) {
        return message.split(PIPE_REGEX);
    }

    public static String getFirstArgument(String message) {
        return message.split(WHITESPACE_REGEX)[0];
    }

    public static String removeFirstArgument(String message) {
        String[] userInputSplit = splitWhitespace(message);
        return message.replaceAll(String.format("^%s\\s*", userInputSplit), "");
    }
}
