package duke.ui;

public class ErrorMessage {

    /* Error messages */
    public static final String DEADLINE_ERROR_MESSAGE = "Sorry please input a valid DEADLINE TASK with date and time "
            + "by using <description> /by <date and time>";
    public static final String EVENT_ERROR_MESSAGE = "Sorry please input a EVENT TASK with date and time by using "
            + "<description> /at <date time>";
    public static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "Please do not input an empty description.\n"
            + "Input a TODO/DEADLINE/EVENT TASK with a valid description by using\n"
            + "TODO <description>\n"
            + "DEADLINE <description> /by <date and time>\n"
            + "EVENT <description> /at <date and time>";
    public static final String TASK_DOES_NOT_EXIST_MESSAGE = "Sorry, task selected does not exist! Please double "
            + "check if task number exist with the list command.";
    public static final String FILE_IO_ERROR_MESSAGE = "Sorry, there was some issues regarding the data files.";
    public static final String UNKNOWN_TASK_TYPE_ERROR_MESSAGE = "Sorry, task type not found ";
}
