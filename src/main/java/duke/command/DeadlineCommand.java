package duke.command;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {

    final public static String COMMAND_WORD = "deadline";
    final public static String TAG_DUE_DATE_DEADLINE = "/by";
    final public static String TAG_DUE_DATE_DEADLINE_FORMAT = "<due date>";

    final public static String MESSAGE_FORMAT = COMMAND_WORD
            + TAG_TASK_DESCRIPTION + " "
            + TAG_DUE_DATE_DEADLINE + " "
            + TAG_DUE_DATE_DEADLINE_FORMAT;

    private String[] arguments;

    public DeadlineCommand(String[] arguments) {
        super();
        this.arguments = arguments;
        this.hasDataChange = true;
    }

    /**
     * Method that calls the task manager that will create the deadline command. The variables arguments[0] is the task
     * description and arguments[1] is the due date.
     */
    @Override
    public void execute() {
        taskManager.createDeadlineTask(arguments[0], arguments[1].trim());
    }

}
