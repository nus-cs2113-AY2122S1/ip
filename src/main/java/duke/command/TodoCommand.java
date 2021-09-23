package duke.command;

public class TodoCommand extends Command{
    final public static String COMMAND_WORD = "todo";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_DESCRIPTION;

    private String arguments;

    public TodoCommand(String arguments) {
        super();
        this.arguments = arguments;
        this.hasDataChange = true;
    }

    @Override
    public void execute(){
        taskManager.createToDoTask(this.arguments);
    }
}
