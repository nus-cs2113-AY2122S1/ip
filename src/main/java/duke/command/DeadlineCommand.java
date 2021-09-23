package duke.command;

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

    @Override
    public void execute() {
        taskManager.createDeadlineTask(arguments[0], arguments[1]);
    }
}
