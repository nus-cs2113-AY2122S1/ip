package duke.command;

public class EventCommand extends Command{
    final public static String COMMAND_WORD = "event";
    final public static String TAG_START_DATE_DEADLINE = "/at";
    final public static String TAG_START_DATE_DEADLINE_FORMAT = "<start date>";

    final public static String MESSAGE_FORMAT = COMMAND_WORD
            + TAG_TASK_DESCRIPTION + " "
            + TAG_START_DATE_DEADLINE + " "
            + TAG_START_DATE_DEADLINE_FORMAT;

    private String[] arguments;

    public EventCommand(String[] arguments) {
        super();
        this.arguments = arguments;
        this.hasDataChange = true;
    }

    @Override
    public void execute(){
        taskManager.createEventTask(arguments[0],arguments[1]);
    }
}
