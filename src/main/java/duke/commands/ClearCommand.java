package duke.commands;

public class ClearCommand extends Command {

    private static final String CLEAR_TASKS_MESSAGE = "Okay! Now your list is empty, you're FREE!";

    public ClearCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        taskManager.clearAllTasks();
        CommandResult result = new CommandResult(CLEAR_TASKS_MESSAGE);
        return result;
    }
}
