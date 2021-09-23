package duke.command;

public class DoneCommand extends Command{
    final public static String COMMAND_WORD = "done";
    final public static String MESSAGE_FORMAT = COMMAND_WORD + TAG_TASK_NUMBER;

    public DoneCommand(int targetIndex) {
        super(targetIndex);
        this.hasDataChange = true;
    }

    @Override
    public void execute(){
        taskManager.setTaskToDone(this.getTaskIndex());
    }
}
