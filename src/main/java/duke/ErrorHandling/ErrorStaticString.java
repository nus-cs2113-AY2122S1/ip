package duke.ErrorHandling;

public class ErrorStaticString {
    public static final String ERROR_NULL = "☹ OOPS!!! Input cannot be empty.";
    public static final String ERROR_UNKNOWN_COMMAND =  "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + "" +
                                                        "Defaulting to Echo. Type !help to see the list of Commands\n";
    public static final String ERROR_EMPTY_LIST = "☹ OOPS!!! List is empty. Add tasks to the list.";
    public static final String ERROR_EMPTY_TODO_LIST = "☹ OOPS!!! There is no To Do task in list. Add To Do tasks to the list.";
    public static final String ERROR_EMPTY_EVENT_LIST = "☹ OOPS!!! There is no Event task in list. Add Events to the list.";
    public static final String ERROR_EMPTY_DEADLINE_LIST = "☹ OOPS!!! There is no Deadline task in list. Add Deadlines to the list.";
    public static final String ERROR_EMPTY_TODO_INPUT = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String ERROR_EMPTY_EVENT_INPUT = "☹ OOPS!!! The description of a event cannot be empty.";
    public static final String ERROR_EMPTY_DEADLINE_INPUT = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String ERROR_EMPTY_ECHO_INPUT = "☹ OOPS!!! The description of a echo cannot be empty.";
    public static final String ERROR_EMPTY_EVENT_TIME = "☹ OOPS!!! The timing of a event cannot be empty.";
    public static final String ERROR_EMPTY_DEADLINE_TIME = "☹ OOPS!!! The due date of a deadline cannot be empty.";
    public static final String ERROR_DONE_TASK_NOT_IN_LIST ="☹ OOPS!!! Task done not found in list";
    public static final String ERROR_DELETE_TASK ="☹ OOPS!!! Cannot remove task that does not exist.";
    public static final String ERROR_LETTER_NOT_FOUND = "☹ OOPS!!! Something went wrong. Do not input space or symbols.";
    public static final String ERROR_FILE_MESSAGE_CREATION = "Something went wrong with creating files";
    public static final String ERROR_FILE_MESSAGE_READING = "Something went wrong with reading the files";
    public static final String ERROR_FILE_MESSAGE_WRITING = "Something went wrong with writing to the files";
}
