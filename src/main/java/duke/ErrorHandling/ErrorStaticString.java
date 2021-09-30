package duke.ErrorHandling;

/**
 * Contain Error Messages
 */
public class ErrorStaticString {
    public static final String ERROR_EMPTY_INPUT = "☹ OOPS!!! Input cannot be empty.";
    public static final String ERROR_UNKNOWN_COMMAND =  "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "" +
                                                        "Defaulting to Echo. Type !help to see the list of Commands\n";

    public static final String ERROR_EMPTY_LIST = "☹ OOPS!!! List is empty. Add tasks to the list.";
    public static final String ERROR_EMPTY_TODO_LIST = "☹ OOPS!!! There is no To Do task in list. Add To Do tasks to the list.";
    public static final String ERROR_EMPTY_EVENT_LIST = "☹ OOPS!!! There is no Event task in list. Add Events to the list.";
    public static final String ERROR_EMPTY_DEADLINE_LIST = "☹ OOPS!!! There is no Deadline task in list. Add Deadlines to the list.";
    public static final String ERROR_EMPTY_DATE_SEARCH_LIST = "☹ OOPS!!! No tasks with date found.";
    public static final String ERROR_EMPTY_WORD_SEARCH_LIST = "☹ OOPS!!! No tasks with input word found.";

    public static final String ERROR_EMPTY_TODO_INPUT = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String ERROR_EMPTY_EVENT_INPUT = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String ERROR_EMPTY_DEADLINE_INPUT = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String ERROR_EMPTY_ECHO_INPUT = "☹ OOPS!!! The description of a echo cannot be empty.";

    public static final String ERROR_EMPTY_EVENT_TIME = "☹ OOPS!!! The timing of the event not detected, use [at] to indicated date and time.";
    public static final String ERROR_EMPTY_DEADLINE_TIME = "☹ OOPS!!! The due date of a deadline not detected, use [by] to indicated date and time.";

    public static final String ERROR_DONE_TASK_NOT_IN_LIST ="☹ OOPS!!! Task done not found in list";
    public static final String ERROR_DONE_TASK_EMPTY ="☹ OOPS!!! Cannot set task as complete without index.";
    public static final String ERROR_DONE_INPUT_FORMAT = "☹ OOPS!!! Use only , and numbers to separate task to set as done.";

    public static final String ERROR_DELETE_TASK ="☹ OOPS!!! Cannot remove task that does not exist.";
    public static final String ERROR_DELETE_TASK_EMPTY ="☹ OOPS!!! Cannot remove task without index.";
    public static final String ERROR_DELETE_INPUT_FORMAT = "☹ OOPS!!! Use only , and numbers to separate task to delete.";

    public static final String ERROR_ARTBOT_LETTER_NOT_FOUND = "☹ OOPS!!! Something went wrong, character input cannot be drawn.\n" +
            "Do not input space or symbols.";
    public static final String ERROR_ARTBOT_INPUT_EMPTY = "☹ OOPS!!! Input cannot be empty.";

    public static final String ERROR_FILE_MESSAGE_CREATION = "☹ OOPS!!! Something went wrong with creating files";
    public static final String ERROR_FILE_MESSAGE_READING = "☹ OOPS!!! Something went wrong with reading the files";
    public static final String ERROR_FILE_MESSAGE_WRITING = "☹ OOPS!!! Something went wrong with writing to the files";
    public static final String ERROR_FILE_MESSAGE_CLEARING = "☹ OOPS!!! Something went wrong with clearing contents of the files";

    public static final String ERROR_DATE_TIME_PARSE = "☹ OOPS!!! Something went wrong with reading date or time\n" +
            "Input will be read in as String\n" + "Format of Date: YYYY-MM-DD\n" + "Format of Time: HH:MM";
    public static final String ERROR_EMPTY_DATE_INPUT = "☹ OOPS!!! Date Searched cannot be Empty.";
    public static final String ERROR_EMPTY_WORD_INPUT = "☹ OOPS!!! Word Searched cannot be Empty.";
}
