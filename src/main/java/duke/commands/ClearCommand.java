package duke.commands;

import duke.tasks.TaskManager;

/** Includes the operations needed to clear the current task list */
public class ClearCommand extends Command {

    private static final String CLEAR_TASKS_MESSAGE = "Okay! Now your list is empty, you're FREE!";

    /** Constructed when the command word of the user input is "clear". */
    public ClearCommand() {
        super();
    }

    @Override
    public CommandResult executeCommand() {
        TaskManager.clearAllTasks();
        CommandResult result = new CommandResult(CLEAR_TASKS_MESSAGE);
        return result;
    }
}
