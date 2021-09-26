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
    public static String getFirstArg(String message) {
        return message.split(WHITESPACE_REGEX)[0];
    }
}
