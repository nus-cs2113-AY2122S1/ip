package duke.exception;

public class DukeException extends Exception {
    public static final String TASK_ALREADY_DONE = "Task already completed.";
    public static final String TASK_ARRAY_FULL = "No more memory to store tasks.";
    public static final String TASK_INDEX_OOB = "Index of task outside range.";
    public static final String TASK_INDEX_INVALID = "Index of task is not a valid integer.";
    public static final String COMMAND_INVALID = "Command given cannot be recognized.";

    public static final String TODO_BLANK_DESC = "Description of todo is blank.";

    public static final String DEADLINE_BLANK_DESC = "Description of deadline is blank.";
    public static final String DEADLINE_NO_SLASH = "Deadline command does not include '/' character.";
    public static final String DEADLINE_BLANK_DATE = "Date of deadline is blank.";

    public static final String EVENT_BLANK_DESC = "Description of event is blank.";
    public static final String EVENT_NO_SLASH = "Event command does not include '/' character.";
    public static final String EVENT_BLANK_DATE = "Date of event is blank.";

    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
