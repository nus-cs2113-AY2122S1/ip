public class DukeException extends Exception {

    protected static final String ERROR_NO_TODO_DESCRIPTION = Ui.INDENT +
            "OOPS!! The description of todo can't be empty.";
    protected static final String ERROR_NO_DEADLINE_DESCRIPTION = Ui.INDENT +
            "OOPS!! The description of deadline can't be empty.";
    protected static final String ERROR_NO_EVENT_DESCRIPTION = Ui.INDENT +
            "OOPS!! The description of event can't be empty.";
    protected static final String ERROR_NO_INDEX = Ui.INDENT +
            "OOPS!! The task's index can't be empty.";
    protected static final String ERROR_INDEX_NOT_NUMBER = Ui.INDENT +
            "OOPS!! The task's index should be a number.";
    protected static final String ERROR_INDEX_OUT_OF_BOUND = Ui.INDENT +
            "OOPS!! The task's index should be positive and " +
            Ui.LINE_SEPARATOR_AND_INDENT + "smaller than the total number of tasks";
    protected static final String ERROR_NO_EVENT_DATE = Ui.INDENT +
            "OOPS!! The date of event can't be empty." + Ui.LINE_SEPARATOR_AND_INDENT +
            "Or you haven't used /on for date";
    protected static final String ERROR_NO_DEADLINE_DATE = Ui.INDENT +
            "OOPS!! The date of deadline can't be empty." + Ui.LINE_SEPARATOR_AND_INDENT +
            "Or you haven't used /by for date";
    protected static final String ERROR_INVALID_COMMAND = Ui.INDENT +
            "OOPS!! I don't understand what that means :-(";
    protected static final String ERROR_NO_KEYWORD = Ui.INDENT +
            "OOPS!! The search keyword can't be empty";

    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
