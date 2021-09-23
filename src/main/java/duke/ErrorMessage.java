/**
 * List of error messages
 */
public class ErrorMessage {
    public static final String EXCEPTION_MISSING_DESCRIPTION_TODO =
            "     ☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String EXCEPTION_MISSING_DESCRIPTION_EVENT =
            "     ☹ OOPS!!! The description of an event cannot be empty.";
    public static final String EXCEPTION_MISSING_DESCRIPTION_DEADLINE =
            "     ☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String EXCEPTION_MESSAGE_UNKNOWN_COMMAND =
            "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_KEYWORD =
            "     ☹ OOPS!!! I'm sorry, but I don't know what is the timeframe of this task :-(";
    public static final String EXCEPTION_MESSAGE_MISSING_PARAMETERS_AFTER_COMMAND =
            "     OOPS! The command requires a parameter e.g. done 2\n" +
                    " The number '2' will be the parameter for the command done";
    public static final String EXCEPTION_MESSAGE_INPUT_NOT_INT =
            "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                    "     Please input a string so that I can understand.";
    public static final String EXCEPTION_INCORRECT_TIME_FORMAT =
            "     Date keyed in is of invalid format\n" +
                    "     Please input date in the following format: YYYY-MM-DD HHMM\n" +
                    "     or YYYY-MM-DD";
    public static final String EXCEPTION_WRONG_INPUT =
            "     invalid input, please try a valid command\n" +
                    "     Commands can be found by typing help";
    public static final String EXCEPTION_NUMBER_FORMAT =
            "     Please input an integer.";
    public static final String EXCEPTION_INDEX_OUT_OF_BOUNDS =
            "     Please input a positive integer that corresponds to the task in list";
}
