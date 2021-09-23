package duke.command;

public class DeleteCommand extends Command{
    final public static String COMMAND_WORD = "delete";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_NUMBER;

    public DeleteCommand(int targetIndex) {
        super(targetIndex);
        this.hasDataChange = true;
    }

    @Override
    public void execute(){
        taskManager.deleteTask(this.getTaskIndex());
    }
}
