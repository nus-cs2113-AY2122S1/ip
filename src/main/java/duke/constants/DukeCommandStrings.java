package duke.constants;

public class DukeCommandStrings extends DukeConstants {

    // Commands recognized by Duke
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String HELP_COMMAND = "help";
    public static final String FIND_COMMAND = "find";
    public static final String EXIT_COMMAND = "bye";

    // Prefixes and regex that help with the parsing of user input
    public static final String WHITESPACE_SEQUENCE = "\\s+";
    public static final String DEADLINE_PREFIX = "/by";
    public static final String EVENT_PREFIX = "/at";

    // Date and time formats
    public static final String DATE_TIME_INPUT_FORMAT = "dd-MM-yyyy HH:mm";
    public static final String DATE_TIME_OUTPUT_FORMAT = "LLL dd yyyy hh:mm a";
}
