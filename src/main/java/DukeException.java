public class DukeException extends Exception {
    protected static final String TASK_ARRAY_FULL = "No more memory to store tasks.";
    protected static final String TASK_INDEX_OOB = "Index of task outside range.";
    protected static final String TASK_INDEX_NOT_VALID = "Index of task is not a valid integer.";

    protected static final String TODO_BLANK_DESC = "Description of todo is blank.";

    protected static final String DEADLINE_BLANK_DESC = "Description of deadline is blank.";
    protected static final String DEADLINE_NO_SLASH = "Deadline command does not include '/' character.";
    protected static final String DEADLINE_BLANK_DATE = "Date of deadline is blank.";

    protected static final String EVENT_BLANK_DESC = "Description of event is blank.";
    protected static final String EVENT_NO_SLASH = "Event command does not include '/' character.";
    protected static final String EVENT_BLANK_DATE = "Date of event is blank.";

    public DukeException (String errorMessage) {
        super(errorMessage);
    }
}
